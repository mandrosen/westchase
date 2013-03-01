<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Email Blast Image Browse</title>
    
    <s:head />
    
    <script type="text/javascript">
    	function selectFile(filename) {
    		window.opener.CKEDITOR.tools.callFunction(1, 'http://www.westchasedistrict.com/app/images/email/' + filename, ''); 
    		window.close();
    	}
    </script>
</head>
<body>

	<div class="files">
	
		<table>
			<thead>
				<tr>
					<th>File Name</th>
				</tr>
			</thead>
			<tbody>
			    <s:iterator value="files" status="status" id="f">
			        <tr class="<s:if test="#status.even">even</s:if><s:else>odd</s:else>">
					    <td>
					    	<s:if test="directory">
					    		<a href="<s:url action="ckeditorBrowse?directory=%{name}" />">
					    			<s:property value="name"/>
					    		</a>
					    	</s:if>
					    	<s:else>
					    		<a href="javascript:selectFile('${f.name}')">
					    			<s:property value="name"/>
					    		</a>
					    	</s:else>
					    </td>
			        </tr>
			    </s:iterator>
			</tbody>
		</table>
	
	</div>

</body>
</html>