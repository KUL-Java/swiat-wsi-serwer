package com.kuljava.swiatwsi.mock;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("mock")
@CrossOrigin
public class MockController {

  @GetMapping("/home")
  public ResponseEntity<MockResponse> homePage() {
    return ResponseEntity.ok(MockResponse.notSecuredResponse());
  }

  @GetMapping("/secured")
  public ResponseEntity<MockResponse> secured() {
    return ResponseEntity.ok(MockResponse.securedResponse());
  }

  @GetMapping("/third")
  public ResponseEntity<MockResponse> third(){
    return ResponseEntity.ok(MockResponse.thirdEndpoint());
  }
}
