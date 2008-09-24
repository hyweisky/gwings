
  
rem  Licensed under the Apache License, Version 2.0 (the "License"); you may not
rem use this file except in compliance with the License. You may obtain a copy of
rem the License at
rem 
rem http://www.apache.org/licenses/LICENSE-2.0
rem 
rem Unless required by applicable law or agreed to in writing, software
rem distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
rem WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
rem License for the specific language governing permissions and limitations under
rem the License.
rem 
rem Copyright 2007 Marcelo Emanoel B. Diniz <marceloemanoel AT gmail.com>, Luciano Broussal <luciano.broussal AT gmail.com>
rem
rem @author Marcelo Emanoel , Luciano Broussal
rem @since 07/03/2007


@java -cp "%~dp0\src;%~dp0\bin;%~dp0\lib\gwt-user.jar;%~dp0\lib\gwt-dev-windows.jar;%~dp0\gwt-widgets.jar" com.google.gwt.dev.GWTCompiler -out "%~dp0\www" %* org.gwings.GWingsDemo
pause