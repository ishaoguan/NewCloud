/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package com.seition.cloud.pro.newcloud.app.face.parser;


import com.seition.cloud.pro.newcloud.app.face.exception.FaceError;

/**
 * JSON解析
 * @param <T>
 */
public interface Parser<T> {
    T parse(String json) throws FaceError;
}
