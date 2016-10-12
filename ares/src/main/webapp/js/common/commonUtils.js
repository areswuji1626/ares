function renderView(tpl, data, viewer){
	var views = Mustache.render(tpl, data); 
	viewer.html(views);
};
function initTables(table){
	table.DataTable(
			{
		        "language": {
		            "lengthMenu": "每页 _MENU_ 条记录",
		            "zeroRecords": "对不起没有对应数据！",
		            "info": "第 _PAGE_ 到 _PAGES_页",
		            "search":"搜索:",
		            "infoEmpty": "No records available",
		            "infoFiltered": "(filtered from _MAX_ total records)",
		            "paginate": {
		                "first":      "第一页",
		                "last":       "最后一页",
		                "next":       "下一页",
		                "previous":   "上一页"
		            },
		        },
		        "pagingType": "full_numbers",
		        "bSort": false, //排序功能 
		        "lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "全部"]],
		        "scrollX": true,
		        "bStateSave": true,
		        "bFilter": true, //过滤功能
		        "sDom": '<"top"f>rt<"bottom"<<"leftfloat pagelengthstyle"l><"leftfloat pagestyle"i><"rightfloat"p>>>',
		        
		    } 
	);
};