<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<f:view>
    <html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="eng">
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
            <title><h:outputText value="#{msgs.sitename}"/></title>
        </head>
        <body>
            <div id="main">
                <div id="header">
                </div>
                <div id="content">
                    <h:form id="mainForm">
                        <h:panelGrid columns="2">
                        	 <h:outputLabel for="name" value="Meno banky" />	
                        	  <h:inputText id="name" value="#{bankController.name}" required="true" requiredMessage=" <- #{errs.nameRequired}"/>
                        	   <h:outputLabel for="web" value="Web banky" />	
                        	  <h:inputText id="web" value="#{bankController.web}"/>
                        	   <h:outputLabel for="email" value="Mail banky" />	
                        	  <h:inputText id="email" value="#{bankController.mail}"/>
                        	   <h:outputLabel for="city" value="Mesto banky" />	
                        	  <h:inputText id="city" value="#{bankController.city}"/>
                        	   <h:outputLabel for="address" value="Adresa banky" />	
                        	  <h:inputText id="address" value="#{bankController.address}"/>
                        	  </h:panelGrid>
                        <h:commandButton value="Create " action="#{bankController.save}"/>
                    </h:form>
                </div>
                <div id="tablediv">
                	
                </div>
                <div id="footer">
                </div>
            </div>
        </body>
    </html>
</f:view>
