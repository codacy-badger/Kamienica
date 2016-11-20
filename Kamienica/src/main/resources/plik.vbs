Set objFSO = CreateObject("Scripting.FileSystemObject")
Set shell = CreateObject("WScript.Shell")
'objStartFolder = "D:\Projekcik VB"

Set objFolder = objFSO.GetFolder(shell.CurrentDirectory)
Set colFiles = objFolder.Files

'Set MyFile = objFSO.CreateTextFile(shell.CurrentDirectory, True)
  'MyFile.WriteLine("test")
  ' MyFile.Close
For Each objFile in colFiles
 
    Wscript.Echo objFile.Name
Next
