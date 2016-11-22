function renderView(tpl, data, viewer){
	var views = Mustache.render(tpl, data); 
	viewer.html(views);
};
function initTables(table){
	table.DataTable(
			{
		        
		    } 
	);
};