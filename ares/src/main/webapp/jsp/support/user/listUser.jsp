<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>用户列表</title>
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
            	用户信息
          </h1>
          <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
            <li><a href="#">查询用户</a></li>
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
                  <table id="userList" class="table table-bordered table-hover">
                    <thead>
                      <tr>
                        <th>用户名</th>
                        <th>用户显示名</th>
                        <th>用户状态</th>
                        <th>生效开始日</th>
                        <th>生效结束日</th>
                        <th>用户类型</th>
                      </tr>
                    </thead>
                    <tbody id="t_body">				
                    </tbody>
                    <tfoot>
                      <tr>
                        <th>用户名</th>
                        <th>用户显示名</th>
                        <th>用户状态</th>
                        <th>生效开始日</th>
                        <th>生效结束日</th>
                        <th>用户类型</th>
                      </tr>
                    </tfoot>
                  </table>
                </div><!-- /.box-body -->
              </div><!-- /.box -->
              
            </div><!-- /.col -->
          </div><!-- /.row -->
        </section><!-- /.content -->
      </div><!-- /.content-wrapper -->
      <footer class="main-footer">
        <div class="pull-right hidden-xs">
          <b>Version</b> 2.3.0
        </div>
        <strong>Copyright &copy; 2014-2015 <a href="http://almsaeedstudio.com">Almsaeed Studio</a>.</strong> All rights reserved.
      </footer>

      <!-- Control Sidebar -->
      <aside class="control-sidebar control-sidebar-dark">
        <!-- Create the tabs -->
        <ul class="nav nav-tabs nav-justified control-sidebar-tabs">
          <li><a href="#control-sidebar-home-tab" data-toggle="tab"><i class="fa fa-home"></i></a></li>
          <li><a href="#control-sidebar-settings-tab" data-toggle="tab"><i class="fa fa-gears"></i></a></li>
        </ul>
      </aside><!-- /.control-sidebar -->
      <!-- Add the sidebar's background. This div must be placed
           immediately after the control sidebar -->
      <div class="control-sidebar-bg"></div>
    </div><!-- ./wrapper -->

    <!-- juicer -->
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
    <script src="<%=request.getContextPath()%>/js/support/user/user.js"></script>

    <!-- page script -->
    <script type="text/javascript">
    $(document).ready(function() {
    	var data = $.parseJSON('${userList}');
    	var tpl = $("#template").html();
    	var viewer = $("#t_body");
    	renderView(tpl, data, viewer);
    	initTables($('#userList'));
    	$('#userList').dataTable();
    });

    </script>
    
    <!-- 显示模板 -->
	<script type="text/x-mustache" id="template">  
	{{#list}}
   	 <tr>
		<td>
			{{user_name}}
		</td>
		<td>
			{{user_show_name}}
		</td>
		<td>
			{{user_status}}
		</td>
		<td>
			{{user_effect_start}}
		</td>
		<td>
			{{user_effect_end}}
		</td>
		<td>
			{{user_type}}
		</td>
	</tr>  
	{{/list}}
  	</script> 
  </body>
</html>
