<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>枚举分类列表</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.5 -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/plugins/bootstrap/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/plugins/font-awesome/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/plugins/ionicons/ionicons.min.css">
    <!-- DataTables -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/plugins/datatables/dataTables.bootstrap.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/plugins/AdminLTE/AdminLTE.min.css">
    <!-- AdminLTE Skins. Choose a skin from the css/skins
         folder instead of downloading all of them to reduce the load. -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/plugins/AdminLTE/skins/_all-skins.min.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body class="hold-transition skin-blue sidebar-mini">
    <div class="wrapper">

	  <jsp:include page="/jsp/support/common/header.jsp"/>
	  <jsp:include page="/jsp/support/common/menu.jsp"/>

      <!-- Content Wrapper. Contains page content -->
      <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
          <h1>
            	枚举分类信息
          </h1>
          <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
            <li><a href="#">枚举</a></li>
            
          </ol>
        </section>

        <!-- Main content -->
        <section class="content">
          <div class="row">
            <div class="col-xs-12">
              <div class="box">
                <div class="box-header">
                  <h3 class="box-title">用户列表</h3>
                </div><!-- /.box-header -->
                <div class="box-body">
                  <table id="codeclassList" class="table table-bordered table-hover">
                    <thead>
                      <tr>
                        <th>枚举类名称</th>
                        <th>枚举类键值</th>
                        <th>枚举类描述</th>
                        <th>创建日期</th>
                        <th>修改日期</th>
                        <th>操作</th>
                      </tr>
                    </thead>
                    <tbody id="t_body">				
                    </tbody>
                    <tfoot>
                      <tr>
                        <th>枚举类名称</th>
                        <th>枚举类键值</th>
                        <th>枚举类描述</th>
                        <th>创建日期</th>
                        <th>修改日期</th>
                        <th>操作</th>
                      </tr>
                    </tfoot>
                  </table>
                </div><!-- /.box-body -->
              </div><!-- /.box -->
              
            </div><!-- /.col -->
          </div><!-- /.row -->
        </section><!-- /.content -->
      </div><!-- /.content-wrapper -->
	  <jsp:include page="/jsp/support/common/footer.jsp"/>
	  <jsp:include page="/jsp/support/common/slideBar.jsp"/>
    </div><!-- ./wrapper -->

    <!-- mustache -->
    <script src="<%=request.getContextPath()%>/js/plugins/mustache/mustache.min.js"></script>
    <!-- jQuery 1.11.4 -->
    <script src="<%=request.getContextPath()%>/js/plugins/jQuery/jquery.js"></script>
    <!-- Bootstrap 3.3.5 -->
    <script src="<%=request.getContextPath()%>/js/plugins/bootstrap/bootstrap.min.js"></script>
    <!-- DataTables -->
    <script src="<%=request.getContextPath()%>/js/plugins/datatables/jquery.dataTables.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/plugins/datatables/dataTables.bootstrap.min.js"></script>
    <!-- SlimScroll -->
    <script src="<%=request.getContextPath()%>/js/plugins/slimScroll/jquery.slimscroll.min.js"></script>
    <!-- FastClick -->
    <script src="<%=request.getContextPath()%>/js/plugins/fastclick/fastclick.min.js"></script>
    <!-- AdminLTE App -->
    <script src="<%=request.getContextPath()%>/js/plugins/AdminLTE/app.min.js"></script>
    <!-- AdminLTE for demo purposes -->
    <script src="<%=request.getContextPath()%>/js/plugins/AdminLTE/demo.js"></script>
    <script src="<%=request.getContextPath()%>/js/support/code/codeclass.js"></script>

    <!-- page script -->
    <script type="text/javascript">
    $(document).ready(function() {
    	var data = $.parseJSON('${codeclassList}');
    	var tpl = $("#template").html();
    	var viewer = $("#t_body");
    	renderView(tpl, data, viewer);
    	initTables($('#codeclassList'));
    	$('#codeclassList').dataTable();
    });

    </script>
    
    <!-- 显示模板 -->
	<script type="text/x-mustache" id="template">  
	{{#list}}
   	 <tr>
		<td>
			{{class_name}}
		</td>
		<td>
			{{class_key}}
		</td>
		<td>
			{{class_desc}}
		</td>
		<td>
			{{create_time}}
		</td>
		<td>
			{{modify_time}}
		</td>
		<td>
			<a class="btn btn-default pull-right" href="<%=request.getContextPath()%>/codeclass/deleteCodeClass/{{class_id}}">删除</a>
		</td>
	</tr>  
	{{/list}}
  	</script> 
  </body>
</html>
