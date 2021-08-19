package com.example.peopleflowdemo.employeemanagement.security;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SecurityProps {


    private List<ResourceMatcher> resourceMatchers;
    private List<ResourceIgnoreMather> resourceIgnoring;
    private Map<String, List<String>> privileges;


    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ResourceMatcher {
        private String method;
        private String path;
        private String access;
    }


    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ResourceIgnoreMather {
        private String method;
        private String path;
    }


}
