<h1>Load Data from CSV files</h1>

<g:if test='${params}'>
 <table border='1'>
 <tr>
   <td>${params.table}
   <td>${params.deletes} Deleted
   <td>${params.loads} Loaded
   <td>${params.errors} Errors
 </tr>
 </table>
</g:if>

<g:link controller='loadCsv' action='loadUsage'>Load Usage</g:link><BR>