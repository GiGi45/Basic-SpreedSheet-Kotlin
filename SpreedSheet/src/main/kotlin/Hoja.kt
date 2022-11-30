class Hoja(var name: String, var countRows: Int, var countColumns: Int) {
    var firstRow: Row? = null //Operador para null (?)
    var lastRow: Row? = null
    //Esta funcionando
    fun addColumn(){
        //Loop para agregar celdas a las filas
        var pFila = firstRow//Pivot a fila
        repeat(countRows){
            pFila?.addCell()//Agregar una celda a esta fila
            pFila = pFila?.nextRow
        }
        countColumns++ //Agregar al contador de columnas
    }
    //Esta funcionando
    fun addRow(){
        val newRow = Row(countRows,countColumns,null)
        if (firstRow == null){
            firstRow = newRow
            lastRow = newRow
            newRow.addCell()
        } else {
            lastRow?.nextRow = newRow
            lastRow = newRow
            repeat(countColumns){
                newRow.addCell()
            }
        }
        countRows++
    }
    //Esta funcionando
    fun changeCellValue(nFila: Int, nColum: Int, valor: Int){
        var pFila = firstRow //Pivot de fila
        repeat(nFila-1){ //Se encuentra la fila
            pFila = pFila?.nextRow
        }
        pFila?.changeValueOfCell(nColum,valor) //Se ingresa la columna para que se cambie dentro de la funcion
    }

    //Esta funcionando
    fun sumCells(cInicio: Int, fInicio: Int, cFinal: Int, fFinal: Int, fRes: Int, cRes: Int){
        var suma = 0 //Variale suma
        var pFila = firstRow //Pivot de las filas
        var pCFila = firstRow //Pivot de las columnas
        var pCombiFila = firstRow //Pivot de la combinacion de filas y columnas
        var posCol = cInicio //Variable para la pos de la columna inicial
        val auxPosCol = posCol

        //Caso para suma de varias filas con varias columnas
        repeat(fInicio-1){//Se recorre hasta llegar a la fila donde se va a empezar a sumar
            pCombiFila = pCombiFila?.nextRow //El puntero pCombiFila se posiciona
        }
        repeat(fFinal+1-fInicio){
            var sumaXFila = 0
            posCol = auxPosCol
            repeat(cFinal+1-cInicio){ //Se repetira la cantidad de espacios que sea el rango //SE NECESITA UNA FORMULA PARA CONTROLAR EL RANGO DE LAS COLUMNAS
                val sumF: Int = pCombiFila!!.returnCellValue(posCol)
                sumaXFila += sumF
                posCol++
            }
            suma += sumaXFila
            pCombiFila = pCombiFila?.nextRow
        }

        var nuevo = firstRow //Propia variable PIVOT para agregar el valor de la suma en la celda deseada
        repeat(fRes-1){ //Poner el resultado en la hoja
            nuevo = nuevo?.nextRow
        }
        nuevo?.changeValueOfCell(cRes,suma) //Encontrar la columna e Ingresar el valor en esa celda
    }

    fun restCells(cInicio: Int, fInicio: Int, cFinal: Int, fFinal: Int, fRes: Int, cRes: Int){ //In development
        var resta = 0 //Variale suma
        var pFila = firstRow //Pivot de las filas
        var pCFila = firstRow //Pivot de las columnas
        var pCombiFila = firstRow //Pivot de la combinacion de filas y columnas
        var posCol = cInicio //Variable para la pos de la columna inicial
        val auxPosCol = posCol

        //Caso para suma de varias filas con varias columnas
        repeat(fInicio-1){//Se recorre hasta llegar a la fila donde se va a empezar a sumar
            pCombiFila = pCombiFila?.nextRow //El puntero pCombiFila se posiciona
        }
        repeat(fFinal+1-fInicio){
            var restaXFila = 0
            posCol = auxPosCol
            repeat(cFinal+1-cInicio){ //Se repetira la cantidad de espacios que sea el rango //SE NECESITA UNA FORMULA PARA CONTROLAR EL RANGO DE LAS COLUMNAS
                val restaF: Int = pCombiFila!!.returnCellValue(posCol)
                restaXFila -= restaF
                posCol++
            }
            resta -= restaXFila
            pCombiFila = pCombiFila?.nextRow
        }

        var nuevo = firstRow //Propia variable PIVOT para agregar el valor de la suma en la celda deseada
        repeat(fRes-1){ //Poner el resultado en la hoja
            nuevo = nuevo?.nextRow
        }
        nuevo?.changeValueOfCell(cRes,resta) //Encontrar la columna e Ingresar el valor en esa celda
    }

    fun averageCells(cInicio: Int, fInicio: Int, cFinal: Int, fFinal: Int, fRes: Int, cRes: Int){
        var suma = 0 //Variale suma
        var count = 0 //Variable contador
        var pFila = firstRow //Pivot de las filas
        var pCFila = firstRow //Pivot de las columnas
        var pCombiFila = firstRow //Pivot de la combinacion de filas y columnas
        var posCol = cInicio //Variable para la pos de la columna inicial
        val auxPosCol = posCol

         //Caso para suma de varias filas con varias columnas
            repeat(fInicio-1){//Se recorre hasta llegar a la fila donde se va a empezar a sumar
                pCombiFila = pCombiFila?.nextRow //Posiciona la fila en la que se empieza la suma de las celdas
            }
            repeat(fFinal+1-fInicio){
                var sumaXFila = 0
                posCol = auxPosCol //Se refresca el valor de la posicion de la columna
                repeat(cFinal+1-cInicio){ //Se repetira la cantidad de espacios que sea el rango //SE NECESITA UNA FORMULA PARA CONTROLAR EL RANGO DE LAS COLUMNAS
                    val sumF: Int = pCombiFila!!.returnCellValue(posCol)
                    sumaXFila += sumF
                    count++ //Aumenta la variable del contador
                    posCol++
                }
                suma += sumaXFila
                pCombiFila = pCombiFila?.nextRow
            }

        var nuevo = firstRow //Propia variable PIVOT para agregar el valor de la suma en la celda deseada
        repeat(fRes-1){ //Poner el resultado en la hoja
            nuevo = nuevo?.nextRow
        }
        nuevo?.changeValueOfCell(cRes,suma/count) //Encontrar la columna e Ingresar el valor en esa celda
    }

    //Esta funcionando
    fun printSheet(name: String) {
        println("       Sheet:    $name")
        var pFila = firstRow //Pivot de la fila
        while (pFila != null){
            pFila.printCellInARow() //Imprimir las celdas de una fila
            pFila = pFila.nextRow
        }
    }
}

fun createSheet(name: String){ //Funcion que permite crear una Hoja

    val newSheet = Hoja(name,0,1)
    //Primero agregar filas
    repeat(6){
        newSheet.addRow()
    }
    repeat(7){
        newSheet.addColumn()
    }

    newSheet.changeCellValue(3,2,1)
    newSheet.changeCellValue(4,2,30)
    newSheet.changeCellValue(5,2,30)
    newSheet.changeCellValue(4,3,5)
    newSheet.changeCellValue(4,4,17)
    newSheet.changeCellValue(4,5,8)
    newSheet.changeCellValue(5,5,10)

    //newSheet.sumCells(2,3,2,5,1,1)
    //newSheet.sumCells(5,4,5,5,3,1)
    newSheet.restCells(2,4,5,5,2,1) //In development
    newSheet.averageCells(2,4,6,4,6,1)
    newSheet.printSheet(name) //Imprime la Hoja
}