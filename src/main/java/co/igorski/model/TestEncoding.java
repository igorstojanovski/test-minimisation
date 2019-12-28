package co.igorski.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "testId",
        "encoding"
})
public class TestEncoding {

    @JsonProperty("testId")
    private String testId;
    @JsonProperty("encoding")
    private String encoding;

    @JsonProperty("testId")
    public String getTestId() {
        return testId;
    }

    @JsonProperty("testId")
    public void setTestId(String testId) {
        this.testId = testId;
    }

    @JsonProperty("encoding")
    public String getEncoding() {
        return encoding;
    }

    @JsonProperty("encoding")
    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

}
