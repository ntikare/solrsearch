package com.nawa.mysearch.util;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Jaxb utility class
 * 
 * @author navneet
 * 
 */
public class JaxbUtil {

	private static final Logger logger = LoggerFactory.getLogger(JaxbUtil.class);

	/**
	 * converts XML input stream to JAXB bean
	 * 
	 * @param clazz
	 * @param is
	 * @return JAXB bean object
	 */
	public static Object readFromXmlStream(Class<?> clazz, InputStream is) {

		try {
			JAXBContext context = JAXBContext.newInstance(clazz);
			return context.createUnmarshaller().unmarshal(is);
		} catch (JAXBException e) {
			logger.error("error in reading xml", e);
			return null;
		}
	}

}
