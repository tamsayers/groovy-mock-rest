function Resource(data) {
	var self = this
	
	self.url = data.url
	self.type = data.type
}

function ResourcesViewModel() {
	var self = this
	
	self.resources = ko.observableArray([])
	
	$.getJSON("/rest/mock/resources", function(allData) {
        var mappedResources = $.map(allData, function(item) { return new Resource(item) });
        self.resources(mappedResources);
    });  
}

ko.applyBindings(new ResourcesViewModel())
