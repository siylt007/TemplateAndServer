/*
 * Copyright 2018 Zhenjie Yan.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.xuexiang.server.component;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.xuexiang.server.utils.ApiUtils;
import com.xuexiang.xutil.net.JsonUtil;
import com.yanzhenjie.andserver.annotation.Converter;
import com.yanzhenjie.andserver.framework.MessageConverter;
import com.yanzhenjie.andserver.framework.body.JsonBody;
import com.yanzhenjie.andserver.http.ResponseBody;
import com.yanzhenjie.andserver.util.IOUtils;
import com.yanzhenjie.andserver.util.MediaType;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

/**
 * 应用消息解析器
 *
 * @author xuexiang
 * @since 2020/8/30 10:40 PM
 */
@Converter
public class AppMessageConverter implements MessageConverter {

    @Override
    public ResponseBody convert(@NonNull Object output, @Nullable MediaType mediaType) {
        return new JsonBody(ApiUtils.successfulJson(output));
    }

    @Nullable
    @Override
    public <T> T convert(@NonNull InputStream stream, @Nullable MediaType mediaType, Type type) throws IOException {
        Charset charset = mediaType == null ? null : mediaType.getCharset();
        if (charset == null) {
            return JsonUtil.fromJson(IOUtils.toString(stream), type);
        }
        return JsonUtil.fromJson(IOUtils.toString(stream, charset), type);
    }
}