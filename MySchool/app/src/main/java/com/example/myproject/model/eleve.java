package com.example.myproject.model;

public class eleve {
    private String email,name,lname, cls;
    private module python,java,javascript,html,php,mysql ;


    public eleve(String email, String name, String lname, String cls, module python, module java, module javascript, module html, module php, module mysql) {
        this.email = email;
        this.name = name;
        this.lname = lname;
        this.cls = cls;
        this.python = python;
        this.java = java;
        this.javascript = javascript;
        this.html = html;
        this.php = php;
        this.mysql = mysql;
    }

    public double moyengeneral(){
        return (python.getMoyen()+java.getMoyen()+javascript.getMoyen()+html.getMoyen()+php.getMoyen()+mysql.getMoyen())/6;

    }

    public double moyenmodule(module m){
        return m.getMoyen();
    }

    public String notesmodule(module m){

        return "le notes de module : note 1: "+m.getNote1()+"note 2: "+m.getNote2()+"note 3: "+m.getNote3();
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getCls() {
        return cls;
    }

    public void setCls(String cls) {
        this.cls = cls;
    }

    public module getPython() {
        return python;
    }

    public void setPython(module python) {
        this.python = python;
    }

    public module getJava() {
        return java;
    }

    public void setJava(module java) {
        this.java = java;
    }

    public module getJavascript() {
        return javascript;
    }

    public void setJavascript(module javascript) {
        this.javascript = javascript;
    }

    public module getHtml() {
        return html;
    }

    public void setHtml(module html) {
        this.html = html;
    }

    public module getPhp() {
        return php;
    }

    public void setPhp(module php) {
        this.php = php;
    }

    public module getMysql() {
        return mysql;
    }

    public void setMysql(module mysql) {
        this.mysql = mysql;
    }
}
