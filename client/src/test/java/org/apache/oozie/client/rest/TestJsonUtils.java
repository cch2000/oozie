/**
 * Copyright (c) 2010 Yahoo! Inc. All rights reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License. See accompanying LICENSE file.
 */
package org.apache.oozie.client.rest;

import junit.framework.TestCase;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.apache.oozie.client.rest.JsonUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Date;

public class TestJsonUtils extends TestCase {

    public void testValidDate() {
        String str = "Thu, 01 Jan 2009 00:00:00 GMT";
        Date date = JsonUtils.parseDateRfc822(str);
        String str1 = JsonUtils.formatDateRfc822(date);
        assertEquals(str, str1);
    }

    public void testInvalidDateStr() {
        Date date = JsonUtils.parseDateRfc822("Xhu, 01 Jan 2009 00:00:00 GMT");
        assertEquals(null, date);
    }

    public void testNullDateStr() {
        assertEquals(null, JsonUtils.parseDateRfc822(null));
    }

    public void testNullDate() {
        assertEquals(null, JsonUtils.formatDateRfc822(null));
    }

    @SuppressWarnings("unchecked")
    public void testGetLong() {
        JSONObject json = new JSONObject();
        json.put("l", 1l);
        assertEquals(1l, JsonUtils.getLongValue(json, "l"));
        assertEquals(0l, JsonUtils.getLongValue(json, "ll"));
    }

    public void testGetListString() {
        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();
        List<String> sList = Arrays.asList("hello", "world");
        array.add("hello");
        array.add("world");
        json.put("list", array);
        assertEquals(array, json.get("list"));
        assertEquals(sList, JsonUtils.getListString(json, "list"));
        assertEquals(sList.size(), JsonUtils.getListString(json, "list").size());
    }

    public void testGetListStringWithNull() {
        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();
        List<String> sList = Arrays.asList("hello", null, "world");
        array.add("hello");
        array.add(null);
        array.add("world");
        json.put("list", array);
        assertEquals(array, json.get("list"));
        assertEquals(sList, JsonUtils.getListString(json, "list"));
        assertEquals(sList.size(), JsonUtils.getListString(json, "list").size());
    }
}
