package com.test;

import java.net.URLEncoder;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.log4j.Logger;

public class TestHttpPoster {
	private HttpClient client=new HttpClient();
	transient public Logger log = Logger.getLogger(this.getClass());
	public void testPut()throws Exception{
		PutMethod putMethod=new PutMethod("http://etcd.qa.military.com:4001/v2/keys/apps/articles-import/props/news.cred.collection.url");
		//putMethod.getParams().setParameter("value", "https://api.newscred.com/collection/79eb193e53366590dc3223785e19d124/items?access_key=e8eacfaf36e6ea64966f8934278e3408&pagesize=200&fields=collection_item.content.category.name+collection_item.content.image.guid+collection_item.content.image.description+collection_item.content.image.caption+collection_item.content.image.created_at+collection_item.content.image.published_at+collection_item.content.image.height+collection_item.content.image.width+collection_item.content.image.orientation+collection_item.content.image.source.name+collection_item.content.image.attribution_text+collection_item.content.image.urls.large+collection_item.content.source.name+collection_item.content.source.guid+collection_item.content.source.website+collection_item.content.author.name+collection_item.content.topic.name+collection_item.content.topic.topic_classification+collection_item.content.topic.topic_subclassification+collection_item.content.topic.guid+collection_item.content.topic.score+collection_item.content.title+collection_item.content.guid+collection_item.content.description+collection_item.content.published_at+collection_item.content.modified_at+collection_item.content.created_at+collection_item.content.metadata.*+collection_item.content.tracking_pixel&sort_order=desc&format=xml");
		String requestEntity="value=\"https://api.newscred.com/collection/79eb193e53366590dc3223785e19d124/items?access_key=e8eacfaf36e6ea64966f8934278e3408&pagesize=200&fields=collection_item.content.category.name+collection_item.content.image.guid+collection_item.content.image.description+collection_item.content.image.caption+collection_item.content.image.created_at+collection_item.content.image.published_at+collection_item.content.image.height+collection_item.content.image.width+collection_item.content.image.orientation+collection_item.content.image.source.name+collection_item.content.image.attribution_text+collection_item.content.image.urls.large+collection_item.content.source.name+collection_item.content.source.guid+collection_item.content.source.website+collection_item.content.author.name+collection_item.content.topic.name+collection_item.content.topic.topic_classification+collection_item.content.topic.topic_subclassification+collection_item.content.topic.guid+collection_item.content.topic.score+collection_item.content.title+collection_item.content.guid+collection_item.content.description+collection_item.content.published_at+collection_item.content.modified_at+collection_item.content.created_at+collection_item.content.metadata.*+collection_item.content.tracking_pixel&sort_order=desc&format=xml\"";
		
		putMethod.setRequestEntity(new StringRequestEntity(requestEntity,"text/plain","UTF-8"));
		int responseStatus = client.executeMethod(putMethod);

		log.debug("Expected HTTP response status 200 " +
				"and got [" + responseStatus + "]");
		byte[] responseBody = putMethod.getResponseBody();
		String response= new String(responseBody, "UTF-8");
		System.out.println(response);


		HttpMethod method = new GetMethod("http://etcd.qa.military.com:4001/v2/keys/apps/articles-import/props/news.cred.collection.url");
		responseStatus = client.executeMethod(method);
		if(responseStatus != 200) {
			throw new IllegalStateException("Expected HTTP response status 200 " +
					"but instead got [" + responseStatus + "]");
		}
		responseBody = method.getResponseBody();
		response= new String(responseBody, "UTF-8");
		System.out.println(response);
		String test="https://api.newscred.com/collection/79eb193e53366590dc3223785e19d124/items?access_key=e8eacfaf36e6ea64966f8934278e3408\u0026pagesize=200\u0026fields=collection_item.content.category.name+collection_item.content.image.guid+collection_item.content.image.description+collection_item.content.image.caption+collection_item.content.image.created_at+collection_item.content.image.published_at+collection_item.content.image.height+collection_item.content.image.width+collection_item.content.image.orientation+collection_item.content.image.source.name+collection_item.content.image.attribution_text+collection_item.content.image.urls.large+collection_item.content.source.name+collection_item.content.source.guid+collection_item.content.source.website+collection_item.content.author.name+collection_item.content.topic.name+collection_item.content.topic.topic_classification+collection_item.content.topic.topic_subclassification+collection_item.content.topic.guid+collection_item.content.topic.score+collection_item.content.title+collection_item.content.guid+collection_item.content.description+collection_item.content.published_at+collection_item.content.modified_at+collection_item.content.created_at+collection_item.content.metadata.*+collection_item.content.tracking_pixel\u0026sort_order=desc\u0026format=xml";
		log.debug(test);
		log.debug(URLEncoder.encode(test,"ascii"));
	}
	public static void main(String[] args) throws Exception{
		TestHttpPoster test=new TestHttpPoster();

		test.testPut();

	}

}
