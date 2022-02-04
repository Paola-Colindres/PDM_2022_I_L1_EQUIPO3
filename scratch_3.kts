import java.lang.IllegalArgumentException

class MainClass() {
    fun main(args: Array<String>) {
        var Enee:ENEE = ENEE()
        Enee.registrar()
    }
}
MainClass().main(arrayOf(""))


open class Factura {
    var numeroFactura: Int
    get() {
        return numeroFactura
    }
    set(value) {
        if (value <= 0) {
            throw IllegalArgumentException("Error: El numero de factura no puede ser <= 0")
        }
    }
    var codigoCliente: Long
    get() {
        return codigoCliente
    }
    set(value) {
        if (value <= 0) {
            throw IllegalArgumentException("Error: El codigo no puede ser <= 0")
        }
    }
    var cliente: String
        get() {
            return cliente
        }
        set(value) {
            if (value.length < 3) {
                throw IllegalArgumentException("Error: El nombre es muy corto")
            }
        }
    var direccion: String
        get() {
            return direccion
        }
        set(value) {
            if (value.length < 5) {
                throw IllegalArgumentException("Error: Ingrese mas de cinco" +
                        " caracteres en la direccion.")
            }
        }
    var fecha: String
        get() {
            return fecha
        }
        set(value) {
            if (value.length < 8) {
                throw IllegalArgumentException("Error: La fecha es invalida")
            }
        }
    var montoPagar: Double
        get() {
            return montoPagar
        }
        set(value) {
            if (value < 0) {
                throw IllegalArgumentException("Error: El monto a pagar no deber ser < 0")
            }
        }
    constructor()
    constructor(factura:Int, codigo:Long, nombre:String, direccion_:String, fecha_:String,
    totalPagar:Double) {
        try {
            if (factura <= 0) {
                throw IllegalArgumentException("Error: El numero de factura no puede ser <= 0")
            }
            if (codigo <= 0) {
                throw IllegalArgumentException("Error: El codigo no debe ser <= 0")
            }
            if (nombre.length < 3) {
                throw IllegalArgumentException("Error: El nombre es muy corto")
            }
            if (fecha_.length < 8) {
                throw IllegalArgumentException("Error: La fecha es invalida")
            }
            if (direccion_.length < 5) {
                throw IllegalArgumentException("Error: Ingrese mas de cinco" +
                        " caracteres en la direccion.")
            }
            if (totalPagar < 0) {
                throw IllegalArgumentException("Error: El monto a pagar no deber ser < 0")
            }
            numeroFactura = factura
            codigoCliente = codigo
            cliente = nombre
            direccion = direccion_
            fecha = fecha_
            montoPagar = totalPagar
        } catch (e: IllegalArgumentException) {
            println(e.message)
        }
    }

    open fun registrar() {
        try {
            print("Ingrese su codigo de cliente: ")
            codigoCliente = readLine().toString().toLong()
            print("Ingrese su nombre: ")
            cliente = readLine().toString()
            print("Ingrese su direccion: ")
            direccion = readLine().toString()
            print("Ingrese la fecha: ")
            fecha = readLine().toString()
            print("Ingrese el monto a Pagar: ")
            montoPagar = readLine().toString().toDouble()
        } catch (e:IllegalArgumentException) {
            println(e.message)
        }

    }
}

class ENEE:Factura {

    constructor()
}

