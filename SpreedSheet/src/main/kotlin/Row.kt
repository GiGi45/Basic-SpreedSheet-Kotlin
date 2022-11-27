class Row (var rowNum: Int, var cellCount: Int, var nextRow: Row? = null){
    var firstCell: Cell? = null
    var lastCell: Cell? = null
    //Esta funcionando
    fun addCell(){
        val newCell = Cell(0,null) //Creacion de una nueva celda
        if (firstCell == null){ //Caso de que no halla celdas
            firstCell = newCell
            lastCell = newCell
        } else{ //Se actualiza el ultimo elemento
            lastCell!!.nextCell = newCell
            lastCell = newCell
        }
        cellCount++
    }
    //Esta funcionando
    fun changeValueOfCell(nColum: Int, newValor: Int) {
        var pCelda = firstCell
        repeat(nColum-1){ //Se posiciona en la columna
            pCelda = pCelda?.nextCell
        }
        pCelda?.cellValue = newValor
    }
    //Esta funcionando
    fun printCellInARow(){
        var pCelda = firstCell
        while (pCelda != null){
            print(" | ${pCelda.cellValue} | ")
            pCelda = pCelda.nextCell
        }
        println()
    }
    //Esta funcionando
    fun returnCellValue(nColum: Int): Int { //Para las operaciones se extrae el valor
        val num: Int
        var pCelda = firstCell
        repeat(nColum-1){
            pCelda = pCelda?.nextCell
        }
        num = pCelda!!.cellValue
        return num
    }
}