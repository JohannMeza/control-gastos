# Guía de Restauración de Base de Datos: ControlGastos

Esta carpeta contiene el respaldo completo de la base de datos `ControlGastos` con todos sus esquemas (`MANTENEDOR`, `FINANZAS`, `SEGURIDAD`, `dbo`), 17 tablas con toda su data histórica (usuarios, categorías, presupuestos, gastos, deudas, abonos, ingresos) y los 48 procedimientos almacenados.

---

## Archivos de Respaldo Generados

1. **`ControlGastos_FullBackup.bak`** (10.6 MB):
   - Respaldo binario nativo de Microsoft SQL Server.
   - Ideal para restaurar en SQL Server 2022 o versiones compatibles mediante SSMS.
2. **`ControlGastos_FullBackup.sql`** (129 KB):
   - Script universal portable en texto plano (UTF-8).
   - Compatible con **cualquier versión o edición** de SQL Server (2017, 2019, 2022, Express, Developer, LocalDB o Azure SQL).

---

## Método A: Restauración mediante Script SQL (Recomendado / Universal)

Este método funciona en cualquier computadora sin importar qué versión de SQL Server tenga instalada.

### Opción 1: Desde SQL Server Management Studio (SSMS)
1. Abre SQL Server Management Studio (SSMS) y conéctate a tu servidor local.
2. Abre el archivo `ControlGastos_FullBackup.sql` (`Archivo > Abrir > Archivo...`).
3. Presiona **F5** o haz clic en **Ejecutar**.
4. El script creará automáticamente la base de datos `ControlGastos`, los esquemas, tablas, datos y los 48 stored procedures.

### Opción 2: Desde Terminal / PowerShell con `sqlcmd`
Ejecuta en la terminal de la nueva computadora:
```powershell
sqlcmd -S localhost -U sa -P TuPassword -i "ControlGastos_FullBackup.sql" -f 65001
```
*(Si usas autenticación de Windows, usa `-E` en lugar de `-U` y `-P`)*:
```powershell
sqlcmd -S localhost -E -i "ControlGastos_FullBackup.sql" -f 65001
```

---

## Método B: Restauración mediante Archivo Binario (.bak)

### Opción 1: Desde SSMS
1. En el Explorador de Objetos de SSMS, haz clic derecho sobre **Bases de datos** > **Restaurar base de datos...**.
2. Selecciona **Dispositivo** y busca el archivo `ControlGastos_FullBackup.bak`.
3. En la pestaña **Opciones**, marca la casilla **Sobrescribir la base de datos existente (WITH REPLACE)**.
4. Haz clic en **Aceptar**.

### Opción 2: Desde T-SQL
```sql
RESTORE DATABASE [ControlGastos]
FROM DISK = N'C:\Ruta\ControlGastos_FullBackup.bak'
WITH FILE = 1, NOUNLOAD, REPLACE, STATS = 10;
GO
```

---

## Conexión de la Aplicación en el Nuevo Equipo

En el proyecto Java, verifica que [ConexionDB.java](file:///d:/Estudios/Superiores/Universidad%20UTP/Ciclo%207/Curso%20Integrador%20I%20Sistema%20Software/Proyecto/control-gastos/src/dao/ConexionDB.java) apunte a las credenciales del SQL Server local:
```java
private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=ControlGastos;encrypt=false";
private static final String USER = "admin"; // o "sa"
private static final String PASSWORD = "1234"; // tu contraseña de SQL Server
```
