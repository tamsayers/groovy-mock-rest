function Resource(data) {
	var self = this

	self.url = decodeURIComponent(data.url)
	self.types = ko.observableArray(data.types)
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

	self.showContent = function(url, type) {
		console.log('type ' +type)
		console.log('url ' +url)
		$.ajax(url, {
			headers: {'Accept': type},
			dataType: 'text',
			success: function(data) {
				$("#content pre").text(data)
				$("#content").dialog("open")
			}
		})
	}
}

$("#content").dialog({ autoOpen: false, modal: true, width: 900, height: 700 });

ko.applyBindings(new ResourcesViewModel())
