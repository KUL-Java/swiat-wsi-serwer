package com.kuljava.swiatwsi.mock;

public class MockResponse {
  private String mockResponse;

  private MockResponse(String mockResponse) {
    this.mockResponse = mockResponse;
  }

  public static MockResponse notSecuredResponse() {
    return new MockResponse("Home Page");
  }

  public static MockResponse securedResponse() {
    return new MockResponse("Secured web endpoint");
  }

  public static MockResponse thirdEndpoint(){
    return new MockResponse("Third");
  }

  public String getMockResponse() {
    return mockResponse;
  }
}
