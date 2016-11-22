<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Question List</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.5 -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/plugins/bootstrap/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/plugins/font-awesome/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/plugins/ionicons/ionicons.min.css">
    <!-- DataTables -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/plugins/datatables/dataTables.bootstrap.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/plugins/AdminLTE/AdminLTE.min.css">
    <!-- AdminLTE Skins. Choose a skin from the css/skins
         folder instead of downloading all of them to reduce the load. -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/plugins/AdminLTE/skins/_all-skins.min.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/common/common.css">
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body class="hold-transition skin-blue sidebar-mini">
    <div class="wrapper">

	  <jsp:include page="/jsp/common/header.jsp"/>
	  <jsp:include page="/jsp/common/menu.jsp"/>

      <!-- Content Wrapper. Contains page content -->
      <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
          <h1>
            	Question Info
          </h1>
          <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i>Home </a></li>
            <li><a href="#">Question</a></li>
            <li class="active">Question List</li>
          </ol>
        </section>

        <!-- Main content -->
        <section class="content">
          <div class="row">
            <div class="col-xs-12">
              <div class="box">
                <div class="box-header">
                  <h3 class="box-title">Question List</h3>
                </div><!-- /.box-header -->
                <div class="box-body">
                  <table id="questionList" class="table table-bordered table-hover">
                    <thead>
                      <tr>
                        <th width="60%">Question Text</th>
                        <th>Question Create</th>
                        <th>Question Modify</th>
                        <th width="5%">Operation</th>
                      </tr>
                    </thead>
                    <tbody id="t_body">				
                    </tbody>
                    <tfoot>
                      <tr>
                        <th>Question Text</th>
                        <th>Question Create</th>
                        <th>Question Modify</th>
                        <th>Operation</th>
                      </tr>
                    </tfoot>
                  </table>
                </div><!-- /.box-body -->
              </div><!-- /.box -->
              
            </div><!-- /.col -->
          </div><!-- /.row -->
        </section><!-- /.content -->
      </div><!-- /.content-wrapper -->
	  <jsp:include page="/jsp/common/footer.jsp"/>
	  <jsp:include page="/jsp/common/slideBar.jsp"/>
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
    <script src="<%=request.getContextPath()%>/js/common/commonUtils_en.js"></script>

    <!-- page script -->
    <script type="text/javascript">
    $(document).ready(function() {
    	var questionList = $.parseJSON('${questionList}');
    	var tpl = $("#template").html();
    	var viewer = $("#t_body");
    	console.info(questionList);	
    	renderView(tpl, questionList, viewer);
    	initTables($('#questionList'));
    	$('#questionList').DataTable();
    	
    });

    </script>
    
    <!-- 显示模板 -->
	<script type="text/x-mustache" id="template">  
	{{#resultSet}}
   	 <tr>
		<td>
			{{questionText}}
		</td>
		<td>
			{{createDate}}
		</td>
		<td>
			{{modifyDate}}
		</td>
		<td>
			<a class="btn btn-default pull-left" href="<%=request.getContextPath()%>/question/editQuestion/{{questionId}}">edit</a>
			<a class="btn btn-default pull-right" href="<%=request.getContextPath()%>/question/deleteQuestion/{{qustionId}}">delete</a>
		</td>
	</tr>  
	{{/resultSet}}
  	</script> 
  </body>
</html>
