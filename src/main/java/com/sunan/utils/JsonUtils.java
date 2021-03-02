package com.sunan.utils;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Component
public class JsonUtils {

	@Autowired
	private ObjectMapper mapper;

	private static final String SUCCESS = "success";
	private static final String DATA = "data";
	private static final String MESSAGE = "message";
	private static final String ERROR = "Error while creating json response.";

	private static Logger logger = LoggerFactory.getLogger(JsonUtils.class);

	public String objectMapperListToJson(List<?> listObject, String message) {

		Map<String, Object> mapObject = new HashMap<>();
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		mapObject.put(SUCCESS, true);
		mapObject.put(MESSAGE, message);
		mapObject.put(DATA, listObject);

		try {
			Writer strWriter = new StringWriter();
			mapper.writeValue(strWriter, mapObject);
			return strWriter.toString();
		} catch (Exception e) {
			logger.error(ERROR, e);
		}
		return null;
	}

	public String objectMapperError(String msg) {
		Map<String, Object> mapObject = new HashMap<>();
		mapObject.put(SUCCESS, false);
		mapObject.put(MESSAGE, msg);
		try {
			Writer strWriter = new StringWriter();
			mapper.writeValue(strWriter, mapObject);
			return strWriter.toString();
		} catch (Exception e) {
			logger.error(ERROR, e);
		}
		return null;
	}
	
	public String objectMapperError(Object object, String msg) {
		Map<String, Object> mapObject = new HashMap<>();
		mapObject.put(SUCCESS, false);
		mapObject.put(DATA, object);
		mapObject.put(MESSAGE, msg);
		try {
			Writer strWriter = new StringWriter();
			mapper.writeValue(strWriter, mapObject);
			return strWriter.toString();
		} catch (Exception e) {
			logger.error(ERROR, e);
		}
		return null;
	}

	public String objectMapperSuccess(String msg) {
		Map<String, Object> mapObject = new HashMap<>();
		mapObject.put(SUCCESS, true);
		mapObject.put(MESSAGE, msg);
		try {
			Writer strWriter = new StringWriter();
			mapper.writeValue(strWriter, mapObject);
			return strWriter.toString();
		} catch (Exception e) {
			logger.error(ERROR, e);
		}
		return null;
	}

	public String objectMapperSuccess(Object object, String msg) {
		Map<String, Object> mapObject = new HashMap<>();
		mapObject.put(SUCCESS, true);
		mapObject.put(DATA, object);
		mapObject.put(MESSAGE, msg);
		try {
			Writer strWriter = new StringWriter();
			mapper.writeValue(strWriter, mapObject);
			return strWriter.toString();
		} catch (Exception e) {
			logger.error(ERROR, e);
		}
		return null;
	}

	public List<String> jsonStringToArray(String jsonString)  {
		 List<String> stringArray = new ArrayList<String>();
		try {
	    JSONArray jsonArray = new JSONArray(jsonString);
	    for (int i = 0; i < jsonArray.length(); i++) {
	        stringArray.add(jsonArray.getString(i));
	    }
		}catch (Exception e) {
		}

	    return stringArray;
	}
}