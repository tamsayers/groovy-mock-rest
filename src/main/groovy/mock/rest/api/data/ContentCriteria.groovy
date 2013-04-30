package mock.rest.api.data

@groovy.transform.Immutable
class ContentCriteria {
    DataUrl url
    String type

    List forTypes() {
        type.split(',').collect { String type ->
            new ContentCriteria(url: url, type: type.trim())
        }
    }
}
