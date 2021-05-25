package com.amoralesch.vdp.spec.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.RecordedRequest;
import okhttp3.mockwebserver.SocketPolicy;
import org.concordion.api.MultiValueResult;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class DynamicControllerFixture extends ApiBase {
    public MultiValueResult http(String method, String uri)
            throws Exception
    {
        return http(method, uri, null, null);
    }

    public MultiValueResult http(String method, String uri, String body)
            throws Exception
    {
        return http(method, uri, body, null);
    }

    public void setFakeResponse(String response)
    {
        fakeServer.enqueue(
            new MockResponse()
                .setResponseCode(200)
                .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .setBody(response));
    }

    public String getExternalRequest()
            throws InterruptedException, JsonProcessingException
    {
        RecordedRequest request = fakeServer.takeRequest();

        return encode(objectMapper.readTree(request.getBody().readUtf8()));
    }
}
