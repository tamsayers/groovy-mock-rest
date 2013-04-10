package mock.rest.api.data

@groovy.transform.Immutable
class DataUrl {
    String path
    String queryString

    String getFullUrl() {
        path + queryString()
    }

    private queryString() {
        (queryString) ? '?' + queryString : ''
    }
}
