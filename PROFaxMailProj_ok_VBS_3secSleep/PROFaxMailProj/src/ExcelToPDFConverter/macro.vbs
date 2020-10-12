Dim Excel
Dim ExcelDoc

Set Excel = CreateObject("Excel.Application")

'Open the Document
Set ExcelDoc = Excel.Workbooks.open("C:\Users\user\Desktop\Cartella Rapporti Excel\SICPA 30 ago 2020, 08 19 16.xlsx")
Excel.ActiveSheet.ExportAsFixedFormat 0, "C:\Users\user\Desktop\Cartella Rapporti PDF\SICPA 30 ago 2020, 08 19 16.pdf" ,0, 1, 0,,,0
Excel.ActiveWorkbook.Close
Excel.Application.Quit