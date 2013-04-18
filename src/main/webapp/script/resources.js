function Resource(data) {
	var self = this
	
	self.url = data.url
	self.types = data.types
}

function ResourcesViewModel() {
	var self = this
	
	self.resources = ko.observableArray([])
	
	$.getJSON("/rest/content/list", function(allData) {
        var mappedResources = $.map(allData.resources, function(item) { return new Resource(item) });
        self.resources(mappedResources);
    });  
}

ko.applyBindings(new ResourcesViewModel())
