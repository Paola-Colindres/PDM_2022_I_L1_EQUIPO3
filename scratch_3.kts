import com.sun.source.tree.CatchTree
import java.time.LocalDate
import java.util.*
import kotlin.Exception
import kotlin.IllegalArgumentException

class MainClass() {
    fun main(args: Array<String>) {
        println(args[0])
        try {
            val enee           : cobroENEE      = cobroENEE()
            val sanaa          : cobroSANAA     = cobroSANAA()
            val recargaTigo    : RecargaTigo    = RecargaTigo()
            val recargaClaro   : RecargaClaro   = RecargaClaro()
            val debitarCuenta  : CuentaDebito   = CuentaDebito()
            val acreditarCuenta: CuentaAcredito = CuentaAcredito()
            do {
                println(
                    "Menu\n1)Cobrar ENEE      2)Cobrar SANAA\n3)Recargar Tigo    " +
                            "4)Recargar Claro\n" +
                            "5)Debitar Cuenta   6)Acreditar Cuenta"
                )
                when (readLine().toString().toInt()) {
                    1 -> {
                        enee.registrar()
                    }
                    2 -> {
                        sanaa.registrar()
                    }
                    3 -> {
                        recargaTigo.mostrarMenu("Tigo")
                    }
                    4 -> {
                        recargaClaro.mostrarMenu("Claro")
                    }
                    5 -> {
                        debitarCuenta.debitar()
                    }
                    6 -> {
                        acreditarCuenta.acreditar()
                    }
                    else -> {
                        println("Elija una Opcion Valida!")
                    }
                }
                println("¿Desea continuar S/N?")
            } while (readLine().toString().contains("S", true))
        } catch (e: Exception) {
            println(e.message)
        }

    }

}
MainClass().main(arrayOf("Titulo"))

//servicios Publicos
open class Factura {
    var codigo: Long = 0
        set(value) {
            if (value.toString().isEmpty()) {
                throw IllegalArgumentException("Error: El codigo de cliente no debe ir vacio")
            }
            if (value <= 0) {
                throw IllegalArgumentException("Error: El codigo no puede ser <= 0")
            }
            field = value
        }
    var cliente: String = ""
        set(value) {
            if (value.isEmpty()) {
                throw IllegalArgumentException("Error: El nombre no debe estar vacio")
            }
            if (value.length < 3) {
                throw IllegalArgumentException("Error: El nombre es muy corto")
            }
            field = value
        }
    var montoPagar: Double = 0.0
        set(value) {
            if (value.toString().isEmpty()) {
                throw IllegalArgumentException("Error: Debe ingresar el monto a pagar")
            }
            if (value <= 0) {
                throw IllegalArgumentException("Error: El monto a pagar no deber ser < 0")
            }
            field = value
        }
    var email: String = ""
        set(value) {
            if (value.isEmpty()) {
                throw IllegalArgumentException("Error: El correo no debe ir vacio")
            }
            if (value.length < 10) {
                throw IllegalArgumentException("Error: Ingrese un correo valido")
            }
            field = value
        }
    var telefono: Long = 0
        set(value) {
            if (value.toString().isEmpty()) {
                throw IllegalArgumentException("Error: El numero de telefono no debe estar vacio")
            }
            if (value.toString().length < 8) {
                throw IllegalArgumentException("Error: El numero de telefono es muy corto")
            }
            if (value.toString().length > 8) {
                throw IllegalArgumentException("Error: El numero de telefono es muy largo")
            }
            if (value < 0) {
                throw IllegalArgumentException("Error: El numero de telefono no puede ser < 0")
            }
            field = value
        }

    constructor()
    constructor(
        codigo_: Long, nombre: String, totalPagar: Double,
        correo: String, numero: Long
    ) {
        try {
            if (codigo_.toString().isEmpty()) {
                throw IllegalArgumentException("Error: Debe ingresar el Codigo")
            }
            if (codigo_ <= 0) {
                throw IllegalArgumentException("Error: El codigo no debe ser <= 0")
            }
            if (nombre.isEmpty()) {
                throw IllegalArgumentException("Error: El nombre no debe estar vacio")
            }
            if (nombre.length < 3) {
                throw IllegalArgumentException("Error: El nombre es muy corto")
            }
            if (totalPagar.toString().isEmpty()) {
                throw IllegalArgumentException("Error: Debe ingresar el monto a pagar")
            }
            if (totalPagar < 0) {
                throw IllegalArgumentException("Error: El monto a pagar no deber ser < 0")
            }
            if (correo.isEmpty()) {
                throw IllegalArgumentException("Error: El correo no debe ir vacio")
            }
            if (correo.length < 10) {
                throw IllegalArgumentException("Error: Ingrese un correo valido")
            }
            if (numero.toString().isEmpty()) {
                throw IllegalArgumentException("Error: El numero de telefono no debe estar vacio")
            }
            if (numero.toString().length < 8) {
                throw IllegalArgumentException("Error: El numero de telefono es muy corto")
            }
            if (numero.toString().length > 8) {
                throw IllegalArgumentException("Error: El numero de telefono es muy largo")
            }
            if (numero < 0) {
                throw IllegalArgumentException("Error: El numero de telefono no puede ser < 0")
            }
            codigo = codigo_
            cliente = nombre
            montoPagar = totalPagar
            email = correo
            telefono = numero
        } catch (e: IllegalArgumentException) {
            println(e.message)
        }
    }

    open fun completarPago() {
        do {
            var condCPago = false
            try {
                println("\nFavor Complete la Informacion")
                print("Correo electronico personal: ")
                email = readLine().toString()
                print("Numero de telefono: ")
                telefono = readLine().toString().toLong()
                println(
                    "\nPago de servicio exitoso!" +
                            "\nSu comprobante de pago sera enviado a esta direccion de correo!"
                )
            } catch (e: Exception) {
                println(e.message + "\n")
                condCPago = true
            }
        } while (condCPago)
    }

    open fun imprimir() {
        println("\nDatos Principales")
        println(
            "Codigo: $codigo" +
                    "\nNombre del Contribuyente: $cliente" +
                    "\nImporte a Pagar: $montoPagar"
        )
    }
}

class cobroENEE : Factura {
    var formaPago: String = ""
        set(value) {
            if (value.isEmpty()) {
                throw IllegalArgumentException("Error: La forma de pago no debe estar vacia")
            }
            if (value.length < 5) {
                throw IllegalArgumentException(
                    "Error: Ingrese mas de cinco digitos en la " +
                            "forma de pago"
                )
            }
            field = value
        }
    var tipoConsumo: String = ""
        set(value) {
            if (value.isEmpty()) {
                throw IllegalArgumentException("Error: Debe ingresar el tipo de consumo")
            }
            if (value.length < 5) {
                throw IllegalArgumentException(
                    "Error: El tipo de consumo debe llevar " +
                            "mas de cinco digitos"
                )
            }
            field = value
        }

    constructor()
    constructor(
        codigo: Long, nombre: String, totalPagar: Double,
        pago: String, consumo: String, correo: String, numero: Long
    ) :
            super(codigo, nombre, totalPagar, correo, numero) {
        try {
            if (pago.isEmpty()) {
                throw IllegalArgumentException("Error: La forma de pago no debe estar vacia")
            }
            if (pago.length < 5) {
                throw IllegalArgumentException(
                    "Error: Ingrese mas de cinco digitos en la " +
                            "forma de pago"
                )
            }
            if (consumo.isEmpty()) {
                throw IllegalArgumentException("Error: Debe ingresar el tipo de consumo")
            }
            if (consumo.length < 5) {
                throw IllegalArgumentException(
                    "Error: El tipo de consumo debe llevar " +
                            "mas de cinco digitos"
                )
            }
            formaPago = pago
            tipoConsumo = consumo
        } catch (e: IllegalArgumentException) {
            println(e.message)
        }
    }

    fun registrar() {
        do {
            var condRegistar = false
            try {
                println("--Pago de Servicio en Linea ENEE--")
                print("Ingrese su Codigo de Cliente: ")
                codigo = readLine().toString().toLong()
                print("Ingrese su Nombre: ")
                cliente = readLine().toString()
                print("Ingrese el monto a Pagar: ")
                montoPagar = readLine().toString().toDouble()
            } catch (e: Exception) {
                println(e.message + "\n")
                condRegistar = true
            }
        } while (condRegistar)
        ingresarTipoConsumo()
        do {
            var continuar: Boolean = false
            println("\nIndique Forma de Pago")
            println("1 - Tarjeta de Credito" + "\n2 - Cuenta de Cheques")
            when (readLine().toString().toInt()) {
                1 -> formaPago = "Tarjeta de Credito"
                2 -> formaPago = "Cuenta de Cheques"
                else -> {
                    println("Respuesta invalida")
                    continuar = true
                }
            }
        } while (continuar)
        imprimir()
        println("\nDesea corregir algun dato? S/N")
        if (readLine().toString().contains("S", true))
            corregirDatos()
        else
            completarPago()
    }

    fun ingresarTipoConsumo() {
        do {
            var continuar: Boolean = false
            println("\nIndique el Tipo de Consumo")
            println(
                "1 - Residencial" +
                        "\n2 - Industrial" +
                        "\n3 - Gobierno" +
                        "\n4 - Autonomo"
            )
            when (readLine().toString().toInt()) {
                1 -> tipoConsumo = "Residencial"
                2 -> tipoConsumo = "Industrial"
                3 -> tipoConsumo = "Gobierno"
                4 -> tipoConsumo = "Autonomo"
                else -> {
                    println("Respuesta invalida")
                    continuar = true
                }
            }
        } while (continuar)
    }

    fun corregirDatos() {
        do {
            var condCorregir = false
            try {
                var respuesta: String
                do {
                    println(
                        "Elija una opcion a corregir"
                                + "\n1 - Codigo de Cliente" +
                                "\n2 - Nombre de Cliente" +
                                "\n3 - Monto a Pagar" +
                                "\n4 - Tipo de Consumo"
                    )
                    when (readLine().toString().toInt()) {
                        1 -> {
                            print("Ingrese el nuevo Codigo: ")
                            codigo = readLine().toString().toLong()
                        }
                        2 -> {
                            print("Ingrese el nuevo Nombre de Cliente: ")
                            cliente = readLine().toString()
                        }
                        3 -> {
                            print("Ingrese el nuevo Monto a Pagar: ")
                            montoPagar = readLine().toString().toDouble()
                        }
                        4 -> {
                            ingresarTipoConsumo()
                        }
                        else -> println("Respuesta Invalida")
                    }
                    println("Desea corregir otro dato? S/N")
                    respuesta = readLine().toString()
                    if (respuesta.contentEquals("n", true)) {
                        completarPago()
                    }
                } while (respuesta.contentEquals("S", true))

            } catch (e: Exception) {
                println(e.message + "\n")
                condCorregir = true
            }
        } while (condCorregir)
    }

    override fun imprimir() {
        super.imprimir()
        println("Tipo de Consumo: $tipoConsumo")
    }
}

class cobroSANAA : Factura {
    var formaPago: String = ""
        set(value) {
            if (value.isEmpty()) {
                throw IllegalArgumentException("Error: La forma de pago no debe estar vacia")
            }
            if (value.length < 5) {
                throw IllegalArgumentException(
                    "Error: Ingrese mas de cinco digitos en la " +
                            "forma de pago"
                )
            }
            field = value
        }
    var categoria: String = ""
        set(value) {
            if (value.isEmpty()) {
                throw IllegalArgumentException("Error: Debe ingresar la categoria")
            }
            if (value.length < 5) {
                throw IllegalArgumentException(
                    "Error: La categoria debe llevar mas de cinco " +
                            "digitos"
                )
            }
            field = value
        }

    constructor()
    constructor(
        codigo: Long, nombre: String, totalPagar: Double,
        pago: String, categoria_: String, correo: String,
        numero: Long
    ) : super(codigo, nombre, totalPagar, correo, numero) {
        try {
            if (pago.isEmpty()) {
                throw java.lang.IllegalArgumentException("Error: La forma de pago no debe estar vacia")
            }
            if (pago.length < 5) {
                throw IllegalArgumentException(
                    "Error: Ingrese mas de cinco digitos en la " +
                            "forma de pago"
                )
            }
            if (categoria_.isEmpty()) {
                throw IllegalArgumentException("Error: Debe ingresar la categoria")
            }
            if (categoria_.length < 5) {
                throw IllegalArgumentException(
                    "Error: La categoria debe llevar mas de cinco " +
                            "digitos"
                )
            }
            formaPago = pago
            categoria = categoria_
        } catch (e: IllegalArgumentException) {
            println(e.message)
        }
    }

    fun registrar() {
        do {
            var condReg = false
            try {
                println("Pago de Servicio en Linea SANAA")
                print("Ingrese su Codigo Comercial: ")
                codigo = readLine().toString().toLong()
                print("Ingrese su Nombre: ")
                cliente = readLine().toString()
                print("Ingrese el monto a Pagar: ")
                montoPagar = readLine().toString().toDouble()
            } catch (e: Exception) {
                println(e.message + "\n")
                condReg = true
            }
        } while (condReg)
        ingresarCategoria()
        do {
            var continuar: Boolean = false
            println("\nIndique Forma de Pago")
            println("1 - Tarjeta de Credito" + "\n2 - Cuenta de Cheques")
            when (readLine().toString().toInt()) {
                1 -> formaPago = "Tarjeta de Credito"
                2 -> formaPago = "Cuenta de Cheques"
                else -> {
                    println("Respuesta invalida")
                    continuar = true
                }
            }
        } while (continuar == true)
        imprimir()
        println("\nDesea corregir algun dato? S/N")
        if (readLine().toString().contains("S", true))
            corregirDatos()
        else
            completarPago()
    }

    fun ingresarCategoria() {
        do {
            var continuar: Boolean = false
            println("\nIndique la Categoria")
            println(
                "1 - Domestica" +
                        "\n2 - Comercial" +
                        "\n3 - Gobierno"
            )
            when (readLine().toString().toInt()) {
                1 -> categoria = "Domestica"
                2 -> categoria = "Comercial"
                3 -> categoria = "Gobierno"
                else -> {
                    println("Respuesta invalida")
                    continuar = true
                }
            }
        } while (continuar)
    }

    fun corregirDatos() {
        do {
            var condC = false
            try {
                var respuesta: String
                do {
                    println(
                        "Elija una opcion a corregir"
                                + "\n1 - Codigo Comercial" +
                                "\n2 - Nombre de Cliente" +
                                "\n3 - Monto a Pagar" +
                                "\n4 - Categoria"
                    )
                    when (readLine().toString().toInt()) {
                        1 -> {
                            print("Ingrese el nuevo Codigo: ")
                            codigo = readLine().toString().toLong()
                        }
                        2 -> {
                            print("Ingrese el nuevo Nombre de Cliente: ")
                            cliente = readLine().toString()
                        }
                        3 -> {
                            print("Ingrese el nuevo Monto a Pagar: ")
                            montoPagar = readLine().toString().toDouble()
                        }
                        4 -> {
                            ingresarCategoria()
                        }
                        else -> println("Respuesta Invalida")
                    }
                    println("Desea corregir otro dato? S/N")
                    respuesta = readLine().toString()
                    if (respuesta.contentEquals("n", true)) {
                        completarPago()
                    }
                } while (respuesta.contentEquals("S", true))
            } catch (e: Exception) {
                println(e.message + "\n")
                condC = true
            }
        } while (condC)
    }

    override fun imprimir() {
        super.imprimir()
        println("Categoria: $categoria")
    }
}

//Recargas
open class Recarga {
    var numero: Long? = 0
        set(value) {
            if (value == null) {
                throw IllegalArgumentException("Debe ingresar el número de teléfono.")
            }
            if (value.toString().length < 8) {
                throw IllegalArgumentException("El número de teléfono es muy corto.")
            }
            if (value.toString().length > 9) {
                throw IllegalArgumentException("El número de teléfono es muy largo.")
            }
            field = value
        }
    private var modoPago: String? = "Efectivo"
        set(value) {
            if (value == null) {
                throw IllegalArgumentException("Debe ingresar el método de pago.")
            }
            if (!value.contains("Efectivo")) {
                throw IllegalArgumentException("El método de pago es incorrecto.")
            }
            if (!value.contains("El método de pago es incorrecto")) {
                throw IllegalArgumentException("El método de pago es incorrecto.")
            }
            field = value
        }
    var valor: Double? = 0.0
        set(value) {
            if (value == null) {
                throw IllegalArgumentException("Debe ingresar un valor.")
            }
            if (value < 0.0) {
                throw IllegalArgumentException("El monto no puede ser menor o igual a 0.")
            }
            field = value
        }

    constructor()
    constructor(pNumero: Long?, pModoPago: String?) {
        try {
            if (pNumero == null) {
                throw IllegalArgumentException("Debe ingresar el número de teléfono.")
            }
            if (pNumero.toString().length < 8) {
                throw IllegalArgumentException("El número de teléfono es muy corto.")
            }
            if (pNumero.toString().length > 8) {
                throw IllegalArgumentException("El número de teléfono es muy largo.")
            }
            if (pModoPago == null) {
                throw IllegalArgumentException("Debe ingresar el método de pago.")
            }
            if (!pModoPago.contains("Efectivo")) {
                throw IllegalArgumentException("El método de pago es incorrecto.")
            }
            if (!pModoPago.contains("El método de pago es incorrecto")) {
                throw IllegalArgumentException("El método de pago es incorrecto.")
            }
            numero = pNumero
            modoPago = pModoPago
        } catch (e: Exception) {
            println(e.message)
        }
    }

    open fun mostrarMenu(tipo: String = "") {
        println("Paquetes y Recargas $tipo")
        println("1)Paquetes  2)Recargas")
    }

    fun obtenerCambio(pPago: Double, pTotal: Double): Double {
        return pPago - pTotal
    }
}

class RecargaTigo : Recarga {
    private var cliente: String? = ""
        set(value) {
            if (value == null) {
                throw IllegalArgumentException("Debe ingresar el número de teléfono.")
            }
            if (value.length < 3) {
                throw IllegalArgumentException("El nombre del cliente es muy corto.")
            }
            if (value.length > 50) {
                throw IllegalArgumentException("El nombre del cliente es muy largo.")
            }
            field = value

        }
    private var pago: Double? = 0.0
        set(value) {
            if (value == null) {
                throw IllegalArgumentException("Debe ingresar un valor.")
            }
            if (value < 0.0) {
                throw IllegalArgumentException("El pago no puede ser menor o igual a 0.")
            }
            field = value

        }

    constructor() : super()
    constructor(pNumero: Long?, pModoPago: String?) : super(pNumero, pModoPago)
    constructor(pNumero: Long?, pModoPago: String?, pPago: Double?) : super(pNumero, pModoPago) {
        try {
            if (pPago == null) {
                throw IllegalArgumentException("Debe ingresar un valor.")
            }
            if (pPago < 0.0) {
                throw IllegalArgumentException("El pago no puede ser menor o igual a 0.")
            }
            pago = pPago
        } catch (e: Exception) {
            println(e.message)
        }
    }

    override fun mostrarMenu(tipo: String) {
        try {
            do {
                super.mostrarMenu(tipo)
                when (readLine().toString().toInt()) {
                    1 -> {
                        println("1)Llamadas.\n2)Mensajes.\n3)Internet.")
                        when (readLine().toString().toInt()) {
                            1 -> {
                                println("1)15min TR/USA(L.18/ 2 dias)")
                                println("2)20min Tigo(L.17 / 2 dias)")
                                println("3)35min TR/USA + 30MB(L.28 / 2 dias)")
                                println("4)60min TR/USA/CAN(L.45 / 3 dias)")
                                println("5)Ilimitada USA/CAN(L.25/ 24 Horas)")
                                println("6)Ilimitada Tigo a Tigo(L.20/ 24 Horas)")
                                when (readLine().toString().toInt()) {
                                    1 -> registrar(
                                        "de llamadas de 15min a TR/USA por 2 dias",
                                        18.0,
                                        1
                                    )
                                    2 -> registrar(
                                        "de llamadas de 20min a Tigo por 2 dias",
                                        17.0,
                                        1
                                    )
                                    3 -> registrar(
                                        "de llamadas de 35min a TR/USA+30MB por 2 dias",
                                        28.0,
                                        1
                                    )
                                    4 -> registrar(
                                        "de llamadas de 60min a TR/USA/CAN por 3 dias",
                                        45.0,
                                        1
                                    )
                                    5 -> registrar(
                                        "de llamadas ilimitadas a USA/CAN por 24 Horas",
                                        25.0,
                                        1
                                    )
                                    6 -> registrar(
                                        "de llamadas ilimitadas de Tigo a Tigo por 24 Horas",
                                        20.0,
                                        1
                                    )
                                    else -> println("Opción no válida")
                                }

                            }
                            2 -> {
                                println("1)70sms TR/USA/CAN(L.15/ 1 dia)")
                                println("2)250sms TR/USA/CAN(L.21 / 3 dias)")
                                println("3)750sms TR/USA/CAN(L.65 / 7 dias)")
                                when (readLine().toString().toInt()) {
                                    1 -> registrar("de 70 mensajes a TR/USA/CAN por 1 dia", 15.0, 1)
                                    2 -> registrar(
                                        "de 250 mensajes a TR/USA/CAN por 3 dias",
                                        21.0,
                                        1
                                    )
                                    3 -> registrar("de 750 mensajes TR/USA/CAN por 7 dias", 65.0, 1)
                                    else -> println("Opción no válida")
                                }
                            }
                            3 -> {
                                println("1)100MB + FB/WhatsApp(L.45/ 1 dia)")
                                println("2)500MB (L.105 / 7 dias)")
                                println("3)400MB (L.51 / 3 dias)")
                                println("4)Facebook Ilimitado(L.17 / 1 dia)")
                                println("5)4GB (L.55 / 1 dia)")
                                println("6)2GB (L.200 / 7 dias)")
                                println("7)1GB (L.100 / 3 dias)")
                                when (readLine().toString().toInt()) {
                                    1 -> registrar(
                                        "de internet de 100MB+FB/WhatsApp por 1 dia",
                                        45.0,
                                        1
                                    )
                                    2 -> registrar("de internet de 500MB por 7 dias", 105.0, 1)
                                    3 -> registrar("de internet de 400MB  por 3 dias", 51.0, 1)
                                    4 -> registrar(
                                        "de internet Facebook Ilimitado por 1 dia",
                                        17.0,
                                        1
                                    )
                                    5 -> registrar("de internet de 4GB por 24 1 dia", 55.0, 1)
                                    6 -> registrar("de internet de 2GB por 24 7 dias", 200.0, 1)
                                    7 -> registrar("de internet de 1GB por 24 3 dias", 100.0, 1)
                                    else -> println("Opción no válida")
                                }
                            }
                        }
                    }
                    2 -> {
                        println("Elija una Opcion:")
                        println("1)Recarga\n2)SuperRecarga")
                        when (readLine().toString().toInt()) {
                            1 -> {
                                registrar(tipo = 2)
                            }
                            2 -> {
                                registrar(tipo = 3)
                            }
                            else -> println("Opción no válida")
                        }
                    }
                }
                print("¿Continuar en el menu de recarga Tigo? S/N:")
            } while (readLine().toString().contains("S", true))
        } catch (e: Exception) {
            println(e.message)
        }
    }

    private fun registrar(descripcion: String = "", total: Double = 0.0, tipo: Int) {
        try {
            valor = total
            println(" - Registro -")
            print(">Nombre del cliente: ")
            cliente = readLine().toString()
            if (tipo == 1) {
                print(">Ingrese el numero de telefono:")
                numero = readLine().toString().toLong()
                if (numero.toString()[0] != '9') {
                    throw IllegalArgumentException("El numero no pertenece a la operadora")
                }
                println("- Metodo de pago -\n1)Efectivo\n2)Tarjeta de crédito o debito")
                if (readLine().toString().toInt() == 1) {
                    print(">Cantidad con la que se paga:")
                    pago = readLine().toString().toDouble()
                    println("| El cambio es de L." + obtenerCambio(pago!!, total))
                }
                println("| El paquete, $descripcion, ha sido activado correctamente.")
            } else {
                recargar(tipo)
            }
        } catch (e: Exception) {
            println(e.message)
        }
    }

    private fun recargar(tipo: Int) {
        try {
            if (tipo == 2) {
                println("Recarga Tigo")
                print(">Ingrese el numero de telefono:")
                numero = readLine().toString().toLong()
                if (numero.toString()[0] != '9') {
                    throw IllegalArgumentException("El numero no pertenece a la operadora")
                }
                print("Valor de la recarga: ")
                valor = readLine().toString().toDouble()
                println("- Metodo de pago -\n1)Efectivo\n2)Targeta de crédito o debito")
                if (readLine().toString().toInt() == 1) {
                    print(">Cantidad con la que se paga:")
                    pago = readLine().toString().toDouble()
                    println("| El cambio es de L." + obtenerCambio(pago!!, valor!!))
                }
                println("La recarga de L.$valor al numero de telefono($numero) ha sido exitosa")

            } else {
                println("SuperRecarga Tigo")
                print(">Ingrese el numero de telefono:")
                numero = readLine().toString().toLong()
                if (numero.toString()[0] != '9') {
                    throw IllegalArgumentException("El numero no pertenece a la operadora")
                }
                print("Valor de la SuperRecarga: ")
                valor = readLine().toString().toDouble()
                println("- Metodo de pago -\n1)Efectivo\n2)Targeta de crédito o debito")
                if (readLine().toString().toInt() == 1) {
                    print(">Cantidad con la que se paga:")
                    pago = readLine().toString().toDouble()
                    println("| El cambio es de L." + obtenerCambio(pago!!, valor!!))
                }
                println("La SuperRecarga de L.$valor al numero de telefono($numero) ha sido exitosa")
                when (valor!!) {
                    in 0.0..9.0 -> {
                        println("Sin extras")
                    }
                    in 10.0..50.0 -> {
                        println(
                            "Incluye L.75 de saldo promo\nWhatsApp chat ilimitado\n+" +
                                    "Minutos ilimitadas de tigo a tigo\n1.7GB por 1 dia"
                        )
                    }
                    in 51.0..100.0 -> {
                        println(
                            "Incluye L.150 de saldo promo\nWhatsApp chat ilimitado\n+" +
                                    "Minutos ilimitadas de tigo a tigo\n5.5GB por 3 dias"
                        )
                    }
                    in 100.0..200.0 -> {
                        println(
                            "Incluye L.300 de saldo promo\nWhatsApp chat ilimitado\n+" +
                                    "Minutos ilimitadas de tigo a tigo\n10.5GB por 7 dias"
                        )
                    }
                    else -> {
                        println(
                            "Incluye L.600 de saldo promo\nWhatsApp chat ilimitado\n+" +
                                    "Minutos ilimitadas de tigo a tigo\n15.5GB por 7 dias"
                        )
                    }
                }
            }
        } catch (e: Exception) {
            println(e.message)
        }
    }
}

class RecargaClaro : Recarga {
    private var cliente: String? = ""
        set(value) {
            if (value == null) {
                throw IllegalArgumentException("Debe ingresar el nombre del cliente.")
            }
            if (value.length < 3) {
                throw IllegalArgumentException("El nombre del cliente es muy corto.")
            }
            if (value.length > 50) {
                throw IllegalArgumentException("El nombre del cliente es muy largo.")
            }
            field = value

        }
    private var pago: Double? = 0.0
        set(value) {
            if (value == null) {
                throw IllegalArgumentException("Debe ingresar un valor.")
            }
            if (value < 0.0) {
                throw IllegalArgumentException("El pago no puede ser menor o igual a 0.")
            }
            field = value

        }

    constructor() : super()
    constructor(pNumero: Long?, pModoPago: String?) : super(pNumero, pModoPago)
    constructor(pNumero: Long?, pModoPago: String?, pPago: Double?) : super(pNumero, pModoPago) {
        try {
            if (pPago == null) {
                throw IllegalArgumentException("Debe ingresar un valor.")
            }
            if (pPago < 0.0) {
                throw IllegalArgumentException("El pago no puede ser menor o igual a 0.")
            }
            pago = pPago
        } catch (e: Exception) {
            println(e.message)
        }
    }

    override fun mostrarMenu(tipo: String) {
        try {
            do {
                super.mostrarMenu(tipo)
                when (readLine().toString().toInt()) {
                    1 -> {
                        println("1)Llamadas.\n2)Mensajes.\n3)Internet.")
                        when (readLine().toString().toInt()) {
                            1 -> {
                                println("1)Llamadas Ilimitadas(L.50/ 2 dias)")
                                println("2)Llamadas Ilimitadas(L.80 / 4 dias)")
                                println("3)Llamadas Ilimitadas(L.150 / 7 dias)")
                                println("4)Llamadas Ilimitadas(L.15 / 3 Horas)")
                                println("5)148min a claro y nacional(L.60 / 1 dia)")
                                println("6)200min a Claro e internacional(L.80 / 2 dias)")
                                when (readLine().toString().toInt()) {
                                    1 -> registrar("de llamadas Ilimitadas por 2 dias", 50.0, 1)
                                    2 -> registrar("de llamadas Ilimitadas por 4 dias", 80.0, 1)
                                    3 -> registrar("de llamadas Ilimitadas por 7 dias", 150.0, 1)
                                    4 -> registrar("de llamadas Ilimitadas por 3 Horas", 15.0, 1)
                                    5 -> registrar(
                                        "de 148min a Claro y nacional por 1 dia",
                                        60.0,
                                        1
                                    )
                                    6 -> registrar(
                                        "de 200min a Claro e internacional por 2 dias",
                                        80.0,
                                        1
                                    )
                                    else -> println("Opción no válida")
                                }

                            }
                            2 -> {
                                println("1)100sms (L.7/ 1 dia)")
                                println("2)250sms (L.12 / 2 dias)")
                                println("3)500sms (L.20 / 4 dias)")
                                println("4)1000sms (L.35 / 7 dias)")
                                when (readLine().toString().toInt()) {
                                    1 -> registrar("de 100sms por 1 dia", 7.0, 1)
                                    2 -> registrar("de 250sms por 2 dias", 12.0, 1)
                                    3 -> registrar("de 500sms por 4 dias", 20.0, 1)
                                    4 -> registrar("de 1000sms por 7 dias", 35.0, 1)
                                    else -> println("Opción no válida")
                                }
                            }
                            3 -> {
                                println("1)300MB(L.15/ 1 dia)")
                                println("2)25GB (L.220 / 10 dias)")
                                println("3)15GB (L.150 / 10 dias)")
                                println("4)12GB (L.100 / 7 dia)")
                                println("5)4GB (L.50 / 1 dia)")
                                println("6)2GB (L.25 / 7 dias)")
                                println("7)6GB (L.75 / 3 dias)")
                                when (readLine().toString().toInt()) {
                                    1 -> registrar("de internet de 300MB por 1 dia", 15.0, 1)
                                    2 -> registrar("de internet de 25GB por 10 dias", 220.0, 1)
                                    3 -> registrar("de internet de 15GB por 10 dias", 150.0, 1)
                                    4 -> registrar("de internet de 12GB por 7 dia", 100.0, 1)
                                    5 -> registrar("de internet de 4GB por 1 dia", 50.0, 1)
                                    6 -> registrar("de internet de 2GB por 7 dias", 25.0, 1)
                                    7 -> registrar("de internet de 6GB por 3 dias", 75.0, 1)
                                    else -> println("Opción no válida")
                                }
                            }
                        }
                    }
                    2 -> {
                        println("1)Recarga\n2)SuperPack")
                        when (readLine().toString().toInt()) {
                            1 -> {
                                registrar(tipo = 2)
                            }
                            2 -> {
                                registrar(tipo = 3)
                            }
                            else -> println("Opción no válida")
                        }
                    }
                }
                print("¿Continuar en el menu de recarga Claro? S/N:")
            } while (readLine().toString().contains("S", true))
        } catch (e: Exception) {
            println(e.message)
        }
    }

    private fun registrar(descripcion: String = "", total: Double = 0.0, tipo: Int) {
        try {
            valor = total
            println(" - Registro -")
            print(">Nombre del cliente: ")
            cliente = readLine().toString()
            if (tipo == 1) {
                print(">Ingrese el numero de telefono:")
                numero = readLine().toString().toLong()
                println("- Metodo de pago -\n1)Efectivo\n2)Targeta de crédito o debito")
                if (readLine().toString().toInt() == 1) {
                    print(">Cantidad con la que se paga:")
                    pago = readLine().toString().toDouble()
                    println("| El cambio es de L." + obtenerCambio(pago!!, total))
                }
                println("| El paquete, $descripcion, ha sido activado correctamente.")
            } else {
                recargar(tipo)
            }
        } catch (e: Exception) {
            println(e.message)
        }
    }

    private fun recargar(tipo: Int) {
        try {
            if (tipo == 2) {
                println("Recarga Claro")
                print(">Ingrese el numero de telefono:")
                numero = readLine().toString().toLong()
                print("Valor de la recarga: ")
                valor = readLine().toString().toDouble()
                println("- Metodo de pago -\n1)Efectivo\n2)Targeta de crédito o debito")
                if (readLine().toString().toInt() == 1) {
                    print(">Cantidad con la que se paga:")
                    pago = readLine().toString().toDouble()
                    println("| El cambio es de L." + obtenerCambio(pago!!, valor!!))
                }
                println("La recarga de L.$valor al numero de telefono($numero) ha sido exitosa")

            } else {
                println("SuperPack Claro")
                print(">Ingrese el numero de telefono:")
                numero = readLine().toString().toLong()
                print("Valor del superpack : ")
                valor = readLine().toString().toDouble()
                println("- Metodo de pago -\n1)Efectivo\n2)Targeta de crédito o debito")
                if (readLine().toString().toInt() == 1) {
                    print(">Cantidad con la que se paga: ")
                    pago = readLine().toString().toDouble()
                    println("| El cambio es de L." + obtenerCambio(pago!!, valor!!))
                }
                println("El SuperPack de L.$valor al numero de telefono($numero) ha sido activado correctamente.")
                when (valor!!) {
                    in 0.0..24.0 -> {
                        println("Sin extras")
                    }
                    in 25.0..50.0 -> {
                        println(
                            "Incluye 2GB de internet.\nLlamadas y mensajes ilimitados a Claro." +
                                    "\n15min a otras redes y USA.\nWhatsApp chat y Facebook ilimitado."
                        )
                    }
                    in 51.0..100.0 -> {
                        println(
                            "Incluye 6GB de internet.\nLlamadas y mensajes ilimitados a Claro." +
                                    "\n30min a otras redes y USA.\nWhatsApp chat y Facebook ilimitado."
                        )
                    }
                    in 100.0..220.0 -> {
                        println(
                            "Incluye 12GB de internet.\nLlamadas y mensajes ilimitados a Claro." +
                                    "\n60min a otras redes y USA.\nWhatsApp chat,Facebook y Twitter ilimitado."
                        )
                    }
                    else -> {
                        println(
                            "Incluye 25GB de internet.\nLlamadas y mensajes ilimitados a Claro." +
                                    "\n100min a otras redes y USA.\nWhatsApp chat,Facebook, Twitter e" +
                                    "Instagram ilimitado."
                        )
                    }
                }
            }
        } catch (e: Exception) {
            println(e.message)
        }
    }

}

//Cuentas

open class Cuenta {
    var numeroCuenta: Long = 0
        set(value) {
            if (value.toString().isEmpty()) {
                throw IllegalArgumentException("El numero de Cuenta Esta vacio")
            }
            if (value <= 0) {
                throw IllegalArgumentException("Error: El No.Cuenta no puede ser <= 0")
            }
            if (value.toString().length < 3) {
                throw IllegalArgumentException("numero de cuenta muy corto")
            }
            field = value
        }
    var cliente: String = ""
        set(value) {
            if (value.isEmpty()) {
                throw IllegalArgumentException("El nombre esta vacio")
            }
            if (value.length < 3) {
                throw IllegalArgumentException("El nombre del cliente es muy corto.")
            }
            if (value.length > 50) {
                throw IllegalArgumentException("El nombre del cliente es muy largo.")
            }
            field = value
        }
    var saldo: Double = 2000.0
        set(value) {
            if (value.toString().isEmpty()) {
                throw IllegalArgumentException("El saldo esta vacio")
            }
            if (value <= 0) {
                throw IllegalArgumentException("El saldo no puede ser menor a cero")
            }
            field = value
        }

    constructor()
    constructor(pnumeroCuenta: Long, pCliente: String, pSaldo: Double) {
        numeroCuenta = pnumeroCuenta
        cliente = pCliente
        saldo = pSaldo
    }

    open fun mostrarInfo() {
        println("--Detalle del Movimiento: --")
        println("Numero de Cuenta:         $numeroCuenta")
        println("Cliente:                  $cliente")
        println("Fecha de la transaccion:  " + obtenerFecha())
    }

    fun obtenerFecha(): String {
        val cal = Calendar.getInstance()
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)
        val hour = cal.get(Calendar.HOUR_OF_DAY)
        val min = cal.get(Calendar.MINUTE) + 1
        var date = "$day/$month/$year $hour:$min"
        return date
    }
}

class CuentaDebito : Cuenta {
    var debito: Double = 0.0
        set(value) {
            if (value.toString().isEmpty()) {
                throw IllegalArgumentException("Monto a debitar esta vacio")
            }
            if (value <= 0.0) {
                throw IllegalArgumentException("El monto no puede ser menor o igual a L. 0.00")
            }
            if (value > 5000) {
                throw IllegalArgumentException("El monto no puede ser mayor a L. 5,000.00")
            }
            if (value > saldo) {
                throw IllegalArgumentException(
                    "No cuenta con saldo disponibe para efectuar el debito!" +
                            "\n saldo disponible: L. $saldo"
                )
            }
            field = value
        }
    var saldoDebito: Double = 0.0
        get() {
            return saldo - debito
        }
    var numeroTransaccion: Long = 0
        set(value) {
            if (value.toString().length < 4) {
                throw IllegalArgumentException("numero de transaccion muy corto")
            }
            if (value.toString().isEmpty()) {
                throw IllegalArgumentException("Numero de Transaccion vacio")
            }
            if (value <= 0) {
                throw IllegalArgumentException("Numero de Transaccion no valido!")
            }
            field = value
        }
    var dni: Long = 0
        set(value) {
            if (value.toString().length < 13) {
                throw IllegalArgumentException("numero de Identificacion es corto")
            }
            if (value.toString().length < 13) {
                throw IllegalArgumentException("numero de Identificacion muy largo")
            }
            if (value.toString().isEmpty()) {
                throw IllegalArgumentException("Numero de identificacion vacio")
            }
            if (value <= 0) {
                throw IllegalArgumentException("Numero de Identidicacion no valido!")
            }
            field = value
        }

    constructor() : super()
    constructor(
        pNumeroCuenta: Long, pCliente: String, pSaldo: Double, pDebito: Double, pNoTransac: Long,
        pDni: Long
    ) : super(pNumeroCuenta, pCliente, pSaldo) {
        try {
            if (pDebito.toString().isEmpty()) {
                throw IllegalArgumentException("Monto a debitar esta vacio")
            }
            if (pDebito <= 0.0) {
                throw IllegalArgumentException("El monto no puede ser menor o igual a L. 0.00")
            }
            if (pDebito > 5000) {
                throw IllegalArgumentException("El monto no puede ser mayor a L. 5,000.00")
            }
            if (pDebito > saldo) {
                throw IllegalArgumentException(
                    "No cuenta con saldo disponibe para efectuar el debito!" +
                            "\n saldo disponible L. $saldo"
                )
            }
            if (pNoTransac.toString().length < 4) {
                throw IllegalArgumentException("numero de transaccion muy corto")
            }
            if (pNoTransac.toString().isEmpty()) {
                throw IllegalArgumentException("Numero de Transaccion vacio")
            }
            if (pNoTransac <= 0) {
                throw IllegalArgumentException("Numero de Transaccion no valido!")
            }
            if (pDni.toString().length < 13) {
                throw IllegalArgumentException("numero de Identificacion es corto")
            }
            if (pDni.toString().length < 13) {
                throw IllegalArgumentException("numero de Identificacion muy largo")
            }
            if (pDni.toString().isEmpty()) {
                throw IllegalArgumentException("Numero de identificacion vacio")
            }
            if (pDni <= 0) {
                throw IllegalArgumentException("Numero de Identidicacion no valido!")
            }
            debito = pDebito
            numeroTransaccion = pNoTransac
            dni = pDni
        } catch (e: Exception) {
            println(e.message)
        }
    }

    fun debitar() {
        do {
            var condDeb = false
            try {
                println("Ingrese el numero de Transaccion:")
                numeroTransaccion = readLine().toString().toLong()
                println("Ingrese el numero de Cuenta a Debitar:")
                numeroCuenta = readLine().toString().toLong()
                println("Ingrese el nombre del Cuentahabiente ")
                cliente = readLine().toString()
                println("Ingrese el Numero de Identificacion")
                dni = readLine().toString().toLong()
                println("Ingrese el monto a retirar:")
                debito = readLine().toString().toDouble()
                mostrarInfo()
                saldo = saldoDebito
                println("--Transacccion Exitosa!--\n")
            } catch (e: Exception) {
                println(e.message + "\n")
                condDeb = true
            }
        } while (condDeb)
    }

    override fun mostrarInfo() {
        super.mostrarInfo()
        println("ID:                       $dni")
        println("Tipo de Movimiento:       retiro")
        println("Monto Retirado:        L. $debito")
        println("Saldo Actual:          L. $saldoDebito")
    }
}

class CuentaAcredito : Cuenta {
    var credito: Double = 0.0
        set(value) {
            if (value.toString().isEmpty()) {
                throw IllegalArgumentException("Monto a debitar esta vacio")
            }
            if (value <= 0.0) {
                throw IllegalArgumentException("El monto no puede ser menor o igual a L. 0.00")
            }
            if (value > 10000) {
                throw IllegalArgumentException("El monto no puede ser mayor a L. 10,000.00")
            }
            field = value
        }
    var saldoCredito: Double = 0.0
        get() {
            return saldo + credito
        }
    var numeroTransaccion: Long = 0
        set(value) {
            if (value.toString().length < 4) {
                throw IllegalArgumentException("numero de transaccion muy corto")
            }
            if (value.toString().isEmpty()) {
                throw IllegalArgumentException("Numero de Transaccion vacio")
            }
            if (value <= 0) {
                throw IllegalArgumentException("Numero de Transaccion no valido!")
            }
            field = value
        }
    var dniDep: Long = 0
        set(value) {
            if (value.toString().length < 13) {
                throw IllegalArgumentException("numero de Identificacion es corto")
            }
            if (value.toString().length < 13) {
                throw IllegalArgumentException("numero de Identificacion muy largo")
            }
            if (value.toString().isEmpty()) {
                throw IllegalArgumentException("Numero de identificacion vacio")
            }
            if (value <= 0) {
                throw IllegalArgumentException("Numero de Identidicacion no valido!")
            }
            field = value
        }
    var depositante: String = ""
    set(value) {
        if (value.isEmpty()) {
            throw IllegalArgumentException("El nombre del depositante esta vacio")
        }
        if (value.length < 3) {
            throw IllegalArgumentException("El nombre del depositante es muy corto.")
        }
        if (value.length > 50) {
            throw IllegalArgumentException("El nombre del depositante es muy largo.")
        }
        field = value
    }

    constructor() : super()
    constructor(
        pnumeroCuenta: Long, pCliente: String, pSaldo: Double, pCredito: Double, pNoTransac: Long,
        pDni: Long, pDepositante : String
    ) :
            super(pnumeroCuenta, pCliente, pSaldo) {
        try {
            if (pCredito.toString().isEmpty()) {
                throw IllegalArgumentException("Monto a debitar esta vacio")
            }
            if (pCredito <= 0.0) {
                throw IllegalArgumentException("El monto no puede ser menor o igual a L. 0.00")
            }
            if (pCredito > 10000) {
                throw IllegalArgumentException("El monto no puede ser mayor a L. 10,000.00")
            }
            if (pNoTransac.toString().length < 4) {
                throw IllegalArgumentException("numero de transaccion muy corto")
            }
            if (pNoTransac.toString().isEmpty()) {
                throw IllegalArgumentException("Numero de Transaccion vacio")
            }
            if (pNoTransac <= 0) {
                throw IllegalArgumentException("Numero de Transaccion no valido!")
            }
            if (pDepositante.isEmpty()) {
                throw IllegalArgumentException("El nombre del depositante esta vacio")
            }
            if (pDepositante.length < 3) {
                throw IllegalArgumentException("El nombre del depositante es muy corto.")
            }
            if (pDepositante.length > 50) {
                throw IllegalArgumentException("El nombre del depositante es muy largo.")
            }
            credito           = pCredito
            numeroTransaccion = pNoTransac
            dniDep            = pDni
            depositante       = pDepositante
        } catch (e: Exception) {
            println(e.message)
        }
    }

    fun acreditar() {
        do {
            var condAc = false
            try {
                println("Ingrese el numero de Transaccion:")
                numeroTransaccion = readLine().toString().toLong()
                println("Ingrese el numero de Cuenta a Creditar:")
                numeroCuenta = readLine().toString().toLong()
                println("Ingrese el nombre del Cuentahabiente a Acreditar ")
                cliente = readLine().toString()
                println("----   . ----")
                println("Ingrese el nombre del depositante")
                depositante = readLine().toString()
                println("Ingrese el Numero de Identificacion del depositante")
                dniDep = readLine().toString().toLong()
                println("Ingrese el monto a Depositar:")
                credito = readLine().toString().toDouble()
                mostrarInfo()
                saldo = saldoCredito
                println("--Transacccion Exitosa!--\n")
            } catch (e: Exception) {
                println(e.message + "\n")
                condAc = true
            }
        } while (condAc)
    }

    override fun mostrarInfo() {
        super.mostrarInfo()
        println("Depositante:              $depositante")
        println("Id depositante:           $dniDep")
        println("Tipo de Movimiento:       Deposito")
        println("Monto Depositado:      L. $credito")
        println("Saldo Actual:          L. $saldoCredito")
    }

}

