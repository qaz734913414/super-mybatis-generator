package org.mybatis.supergen.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * xml解析util
 * 
 * @author hncdyj123@163.com
 * @date 2016年9月28日
 */
public class XmlUtil {
	/**
	 * 获取mybatis配置文件中的table名
	 * 
	 * @return
	 * @throws DocumentException
	 */
	@SuppressWarnings("rawtypes")
	public static List<String> getXmlTableName() throws DocumentException {
		List<String> tableList = new ArrayList<String>();
		String genCfg = "/mbgConfiguration.xml";
		File configFile = new File(XmlUtil.class.getResource(genCfg).getFile());
		SAXReader reader = new SAXReader();
		Document document = reader.read(configFile);
		Element root = document.getRootElement();
		Element element = root.element("context");
		for (Iterator it = element.elementIterator(); it.hasNext();) {
			Element tableElement = (Element) it.next();
			if (tableElement.getName().equalsIgnoreCase("table")) {
				Attribute attribute = tableElement.attribute("tableName");
				tableList.add(attribute.getText());
			}
		}
		return tableList;
	}

	/**
	 * 获取JDBC连接
	 * 
	 * @return
	 * @throws DocumentException
	 */
	@SuppressWarnings("rawtypes")
	public static Map<String, String> getJdbcConnection() throws DocumentException {
		Map<String, String> jdbcConnectionMap = new HashMap<String, String>();
		String genCfg = "/mbgConfiguration.xml";
		File configFile = new File(XmlUtil.class.getResource(genCfg).getFile());
		SAXReader reader = new SAXReader();
		Document document = reader.read(configFile);
		Element root = document.getRootElement();
		Element element = root.element("context");
		for (Iterator it = element.elementIterator(); it.hasNext();) {
			Element tableElement = (Element) it.next();
			if (tableElement.getName().equalsIgnoreCase("jdbcConnection")) {
				Attribute driverClass = tableElement.attribute("driverClass");
				jdbcConnectionMap.put("driverClass", driverClass.getText());
				Attribute connectionURL = tableElement.attribute("connectionURL");
				jdbcConnectionMap.put("connectionURL", connectionURL.getText());
				Attribute userId = tableElement.attribute("userId");
				jdbcConnectionMap.put("userId", userId.getText());
				Attribute password = tableElement.attribute("password");
				jdbcConnectionMap.put("password", password.getText());
			}
		}
		return jdbcConnectionMap;
	}
}