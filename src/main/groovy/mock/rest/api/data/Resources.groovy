package mock.rest.api.data

import org.codehaus.jackson.annotate.JsonProperty

@groovy.transform.Immutable
class Resources {
    @JsonProperty("resources")
    List<Resource> list
}
