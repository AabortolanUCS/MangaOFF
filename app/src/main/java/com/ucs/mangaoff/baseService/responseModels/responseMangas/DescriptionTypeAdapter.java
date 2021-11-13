package com.ucs.mangaoff.baseService.responseModels.responseMangas;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.ucs.mangaoff.baseService.responseModels.baseResponse.ResponseEnglish;

import java.io.IOException;

public class DescriptionTypeAdapter implements TypeAdapterFactory {

    @Override
    @SuppressWarnings("unchecked")
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        if (type.getRawType()!= ResponseEnglish.class) return null;

        TypeAdapter<ResponseEnglish> defaultAdapter = (TypeAdapter<ResponseEnglish>) gson.getDelegateAdapter(this, type);
        return (TypeAdapter<T>) new MyResultObjectAdapter(defaultAdapter);
    }

    public class MyResultObjectAdapter extends TypeAdapter<ResponseEnglish> {

        protected TypeAdapter<ResponseEnglish> defaultAdapter;


        public MyResultObjectAdapter(TypeAdapter<ResponseEnglish> defaultAdapter) {
            this.defaultAdapter = defaultAdapter;
        }

        @Override
        public void write(JsonWriter out, ResponseEnglish value) throws IOException {
            defaultAdapter.write(out, value);
        }

        @Override
        public ResponseEnglish read(JsonReader in) throws IOException {
            if (in.peek() == JsonToken.BEGIN_ARRAY) {
                in.skipValue();
                return null;
            }
            return defaultAdapter.read(in);
        }
    }
}
