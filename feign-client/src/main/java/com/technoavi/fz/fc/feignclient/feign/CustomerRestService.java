package com.technoavi.fz.fc.feignclient.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="CUSTOMER-SERVICE")
public interface CustomerRestService {

      @GetMapping("/book/data")
      public String getBookData();

}