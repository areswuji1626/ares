<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
      <!-- Left side column. contains the logo and sidebar -->
      <aside class="main-sidebar">
        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar">
          <!-- Sidebar user panel -->
          <div class="user-panel">
            <div class="pull-left image">
              <img src="<%=request.getContextPath()%>/images/AdminLTE/user2-160x160.jpg" class="img-circle" alt="User Image">
            </div>
            <div class="pull-left info">
              <p>Alexander Pierce</p>
              <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
            </div>
          </div>
          <!-- search form -->
          <form action="#" method="get" class="sidebar-form">
            <div class="input-group">
              <input type="text" name="q" class="form-control" placeholder="Search...">
              <span class="input-group-btn">
                <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i></button>
              </span>
            </div>
          </form>
          <!-- /.search form -->
          <!-- sidebar menu: : style can be found in sidebar.less -->
          <ul class="sidebar-menu">
            <li class="header">后台功能</li>
            <li class="treeview">
              <a href="#">
                <i class="fa fa-dashboard"></i> <span>用户管理</span> <i class="fa fa-angle-left pull-right"></i>
              </a>
              <ul class="treeview-menu">
                <li><a href="<%=request.getContextPath()%>/user/listUserView"><i class="fa fa-circle-o"></i>查询用户</a></li>
                <li><a href="<%=request.getContextPath()%>/user/insertUserView"><i class="fa fa-circle-o"></i>新建用户</a></li>
              </ul>
            </li>
            <li class="treeview">
              <a href="#">
                <i class="fa fa-dashboard"></i> <span>枚举管理</span> <i class="fa fa-angle-left pull-right"></i>
              </a>
              <ul class="treeview-menu">
                <li><a href="<%=request.getContextPath()%>/codeclass/listCodeClassView"><i class="fa fa-circle-o"></i>枚举分类管理</a></li>
                <li><a href="<%=request.getContextPath()%>/codeclass/insertCodeClassView"><i class="fa fa-circle-o"></i>创建枚举分类</a></li>
                <li><a href="<%=request.getContextPath()%>/code/listCodeView"><i class="fa fa-circle-o"></i>查询枚举</a></li>
                <li><a href="<%=request.getContextPath()%>/code/insertCodeView"><i class="fa fa-circle-o"></i>创建枚举</a></li>
              </ul>
            </li>
            <li class="treeview">
              <a href="#">
                <i class="fa fa-dashboard"></i> <span>代码生成</span> <i class="fa fa-angle-left pull-right"></i>
              </a>
              <ul class="treeview-menu">
                <li><a href="<%=request.getContextPath()%>/codegen/insertDataSourceView"><i class="fa fa-circle-o"></i>创建数据源</a></li>
                <li><a href="<%=request.getContextPath()%>/codegen/listDataSourceView"><i class="fa fa-circle-o"></i>数据源管理</a></li>
              </ul>
            </li>
            <li class="header">前台功能</li>
            <li class="treeview">
              <a href="#">
                <i class="fa fa-dashboard"></i> <span>图片处理</span> <i class="fa fa-angle-left pull-right"></i>
              </a>
              <ul class="treeview-menu">
                <li><a href="<%=request.getContextPath()%>/picoperation/uploadPicView"><i class="fa fa-circle-o"></i>上传图片</a></li>
              </ul>
            </li>
          </ul>
        </section>
        <!-- /.sidebar -->
      </aside>