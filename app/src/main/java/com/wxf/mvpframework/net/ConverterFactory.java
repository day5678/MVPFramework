package com.wxf.mvpframework.net;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonWriter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okio.Buffer;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * <p>自定义的数据返回转换器</p>
 * 去除了不需要的字段 如code message..<br/>
 * code == 1时会抛出 api异常 ,304时 抛出踢人异常 等.
 *
 * @author wxf
 */
public class ConverterFactory extends Converter.Factory {

    private final Gson gson;

    public static ConverterFactory create() {
        return create(new Gson());
    }

    public static ConverterFactory create(Gson gson) {
        return new ConverterFactory(gson);
    }


    private ConverterFactory(Gson gson) {
        if (gson == null)
            throw new NullPointerException("gson == null");
        this.gson = gson;
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new ResponseBodyConverter<>(adapter);
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new RequestBodyConverter<>(gson, adapter);
    }


    public class ResponseBodyConverter<T> implements Converter<ResponseBody, T> {
        private final TypeAdapter<T> adapter;

        ResponseBodyConverter(TypeAdapter<T> adapter) {
            this.adapter = adapter;
        }

        @Override
        public T convert(ResponseBody value) throws IOException {
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(value.string());
                throwException(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
//                throw new ResponseFormatException("responseData format error");
            }
            //处理data为空
            if (jsonObject.isNull("data"))
                return null;
            try {
                //截取code msg;
                String data = jsonObject.getString("data");

                Log.i("data", ".." + data + "...");

                if (isLong(data)) {
                    String newData = "{\"data\":\"%s\"}";
                    Log.v("ConverterFactory", "转换后的" + newData);
                    return adapter.fromJson(String.format(newData, data));
                }
                if (isBoolean(data)) {
                    String newData = "{\"data\":\"%s\"}";
                    Log.v("ConverterFactory", "转换后的" + newData);
                    return adapter.fromJson(String.format(newData, data));
                }

                if (isString(data)) {
                    String newData = "{\"data\":\"%s\"}";
                    Log.v("ConverterFactory", "转换后的" + newData);
                    return adapter.fromJson(String.format(newData, data));
                }

                Log.v("ConverterFactory", data);
                return adapter.fromJson(data);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        private boolean isString(String value) {
            if (null != value) {
                boolean contains = value.contains(":");
                boolean contains1 = value.contains("[");
                boolean contains2 = value.contains("]");
                return !(contains || contains1 || contains2);
            }
            return false;
        }

        private boolean isLong(String value) {
            try {
                Long.parseLong(value);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }

        private boolean isBoolean(String value) {
            if (null != value && (value.equals("true") || value.equals("false"))) {
                return true;
            }
            return false;
        }

        /**
         * 抛出异常,如果存在的话
         *
         * @param jsonObject responsedata
         */
        private void throwException(JSONObject jsonObject) throws JSONException {
//            if (!jsonObject.has("code")) {
//                throw new ResponseFormatException("responseData format error");
//            }
//            int code = jsonObject.getInt("code");
//            switch (code) {
//                case 0://接口异常
//                    throw new ApiException(jsonObject.getString("message"));
//                case 1://正常
//                    break;
//                case 403://踢人
//                    throw new AuthenticationException("invalid token , code is " + code);
//                default://未知
//                    throw new UnknownException("unknown error , code is " + code);
//            }

        }
    }

    public class RequestBodyConverter<T> implements Converter<T, RequestBody> {
        private final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
        private final Charset UTF_8 = Charset.forName("UTF-8");

        private final Gson gson;
        private final TypeAdapter<T> adapter;

        RequestBodyConverter(Gson gson, TypeAdapter<T> adapter) {
            this.gson = gson;
            this.adapter = adapter;
        }

        @Override
        public RequestBody convert(T value) throws IOException {
            Buffer buffer = new Buffer();
            Writer writer = new OutputStreamWriter(buffer.outputStream(), UTF_8);
            JsonWriter jsonWriter = gson.newJsonWriter(writer);
            adapter.write(jsonWriter, value);
            jsonWriter.flush();
            return RequestBody.create(MEDIA_TYPE, buffer.readByteString());
        }
    }
}
