package com.wuky.search.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wuky.commons.pojo.TbItemChild;
import com.wuky.dubbo.service.TbItemCatDubboService;
import com.wuky.dubbo.service.TbItemDescDubboService;
import com.wuky.dubbo.service.TbItemDubboService;
import com.wuky.pojo.TbItem;
import com.wuky.pojo.TbItemCat;
import com.wuky.pojo.TbItemDesc;
import com.wuky.search.service.TbItemService;


@Service
public class TbItemServiceImpl implements TbItemService
{
	@Reference
	private TbItemDubboService tbItemDubboServiceImpl;
	@Reference
	private TbItemCatDubboService TbItemCatDubboServiceImpl;
	@Reference
	private TbItemDescDubboService tbItemDescDubboServiceImpl;

	@Resource
	private CloudSolrClient solrClient;

	public void init() throws SolrServerException, IOException
	{
		//查询所有正常的商品
		final List<TbItem> listItem = tbItemDubboServiceImpl.selAllByStatus((byte) 1);
		for (final TbItem tbItem : listItem)
		{
			//商品对应类目信息
			final TbItemCat cat = TbItemCatDubboServiceImpl.selById(tbItem.getCid());
			//商品对应的描述信息
			final TbItemDesc desc = tbItemDescDubboServiceImpl.selByItemid(tbItem.getId());

			final SolrInputDocument docs = new SolrInputDocument();

			docs.addField("id", tbItem.getId());
			docs.addField("item_title", tbItem.getTitle());
			docs.addField("item_sell_point", tbItem.getSellPoint());
			docs.addField("item_price", tbItem.getPrice());
			docs.addField("item_image", tbItem.getImage());
			docs.addField("item_category_name", cat.getName());
			docs.addField("item_desc", desc.getItemDesc());

			solrClient.add(docs);
		}
		solrClient.commit();
	}

	public Map<String, Object> selByQuery(final String query, final int page, final int rows)
			throws SolrServerException, IOException
	{
		final SolrQuery params = new SolrQuery();
		//设置分页条件
		params.setStart(rows * (page - 1));
		params.setRows(rows);
		//设置条件
		params.setQuery("item_keywords:" + query);

		//设置高亮
		params.setHighlight(true);
		params.addHighlightField("item_title");
		params.setHighlightSimplePre("<span style='color:red'>");
		params.setHighlightSimplePost("</span>");
		final QueryResponse response = solrClient.query(params);
		final List<TbItemChild> listChild = new ArrayList<TbItemChild>();
		//未高亮内容
		final SolrDocumentList listSolr = response.getResults();
		//高亮内容
		final Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();

		for (final SolrDocument doc : listSolr)
		{
			final TbItemChild child = new TbItemChild();

			child.setId(Long.parseLong(doc.getFieldValue("id").toString()));
			final List<String> list = highlighting.get(doc.getFieldValue("id")).get("item_title");
			if (list != null && list.size() > 0)
			{
				child.setTitle(list.get(0));
			}
			else
			{
				child.setTitle(doc.getFieldValue("item_title").toString());
			}
			child.setPrice((Long) doc.getFieldValue("item_price"));
			final Object image = doc.getFieldValue("item_image");
			child.setImages(image == null || image.equals("") ? new String[1] : image.toString().split(","));
			child.setSellPoint(doc.getFieldValue("item_sell_point").toString());

			listChild.add(child);
		}
		final HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("itemList", listChild);
		resultMap.put("totalPages",
				listSolr.getNumFound() % rows == 0 ? listSolr.getNumFound() / rows : listSolr.getNumFound() / rows + 1);
		return resultMap;
	}

	public int add(final Map<String, Object> map, final String desc) throws SolrServerException, IOException
	{
		final SolrInputDocument doc = new SolrInputDocument();
		doc.setField("id", map.get("id"));
		doc.setField("item_title", map.get("title"));
		doc.setField("item_sell_point", map.get("sellPoint"));
		doc.setField("item_price", map.get("price"));
		doc.setField("item_image", map.get("image"));
		doc.setField("item_category_name", TbItemCatDubboServiceImpl.selById((Integer) map.get("cid")).getName());
		doc.setField("item_desc", desc);
		final UpdateResponse response = solrClient.add(doc);
		solrClient.commit();
		if (response.getStatus() == 0)
		{
			return 1;
		}
		return 0;
	}

}
