function Resource(data) {
	var self = this

	self.url = data.url
	self.types = data.types
}

function ResourcesViewModel() {
	var self = this

	self.resources = ko.observableArray([])

	$.getJSON("/rest/content/list", function(allData) {
		var mappedResources = $.map(allData.resources, function(item) {
			return new Resource(item)
		})
		self.resources(mappedResources)
	})

	self.showContent = function(resource) {
		$.ajax(resource.url, {
			headers: {'Accept':resource.types[0]},
			dataType: 'text',
			success: function(data) {
				alert(data.toString())
			}
		})
	}
}

ko.applyBindings(new ResourcesViewModel())
