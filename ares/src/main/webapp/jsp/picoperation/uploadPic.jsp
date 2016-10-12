<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>图片截取</title>
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
	<!-- date picker -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/plugins/datepicker/bootstrap-datepicker3.min.css">
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
                                             图片处理
          </h1>
          <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
            <li><a href="#">图片处理</a></li>
            <li class="active">上传图片</li>
          </ol>
        </section>

        <!-- Main content -->
        <section class="content">
          <div class="row">
            <div class="col-xs-12">
              <div class="box">
                <div class="box-header">
                  <h3 class="box-title">上传图片</h3>
                </div><!-- /.box-header -->
                <div class="box box-info">
					<div class="box-header with-border">
						<form method="post" action="./uploadPic" enctype="multipart/form-data" class="form-horizontal" name="pic_form" >
						<div class="form-group">
							<label for="code_name" class="col-sm-4 control-label">图片地址</label>
							<div class="col-sm-6">
							<input type="file" name="file" class="form-control"/>
							</div>
						</div>
						<div class="box-footer">
                    		<button type="reset" class="btn btn-default pull-right" id="cancel">取消</button>
                    		<button type="submit" class="btn btn-info pull-right" id="submit">保存</button>
                  		</div><!-- /.box-footer -->
						</form>
					</div>
                </div><!-- /.box-body -->
              </div><!-- /.box -->
            </div><!-- /.col -->
          </div><!-- /.row -->
        </section><!-- /.content -->
      </div><!-- /.content-wrapper -->
	  <jsp:include page="/jsp/common/footer.jsp"/>
	  <jsp:include page="/jsp/common/slideBar.jsp"/>

    </div><!-- ./wrapper -->
    <!-- mustache -->
    <script src="<%=request.getContextPath()%>/js/plugins/mustache/mustache.min.js"></script>
    <!-- jQuery 2.1.4 -->
    <script src="<%=request.getContextPath()%>/js/plugins/jQuery/jQuery-2.1.4.min.js"></script>
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
    <!-- InputMask -->
    <script src="<%=request.getContextPath()%>/js/plugins/input-mask/jquery.inputmask.js"></script>
    <script src="<%=request.getContextPath()%>/js/plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
    <script src="<%=request.getContextPath()%>/js/plugins/input-mask/jquery.inputmask.extensions.js"></script>
    <script src="<%=request.getContextPath()%>/js/plugins/datepicker/bootstrap-datepicker.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/support/code/codeclass.js"></script>
    <!-- page script -->
    <script>
      $(document).ready(function() {

      });
    </script>
  </body>
</html>
