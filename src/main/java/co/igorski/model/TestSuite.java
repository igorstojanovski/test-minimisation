package co.igorski.model;

import java.util.HashMap;
import java.util.Map;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "testEncodings"
})
public class TestSuite {

    @JsonProperty("testEncodings")
    private List<TestEncoding> testEncodings = null;

    @JsonProperty("testEncodings")
    public List<TestEncoding> getTestEncodings() {
        return testEncodings;
    }

    @JsonProperty("testEncodings")
    public void setTestEncodings(List<TestEncoding> testEncodings) {
        this.testEncodings = testEncodings;
    }

}
