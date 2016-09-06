package com.embracesource.scala.classesAndProperties

//Auxiliary constructors are defined by creating methods named this.
//Each auxiliary constructor must begin with a call to a previously defined constructor.
//Each constructor must have a different signature.
//One constructor calls another constructor with the name this.

class Pizza(var crustSize: Int, var crustType: String) {
  // one-arg auxiliary constructor
  def this(crustSize: Int) {
    this(crustSize, Pizza.DEFAULT_CRUST_TYPE)
  }
  
  // one-arg auxiliary constructor
  // def this(crustType: String) {
  //   this(Pizza.DEFAULT_CRUST_SIZE, crustType)
  // }
  
  // one-arg auxiliary constructor
  def this(crustType: String) {
	  this(Pizza.DEFAULT_CRUST_SIZE)
	  this.crustType = Pizza.DEFAULT_CRUST_TYPE
  }
  
  // zero-arg auxiliary constructor
  def this() {
    this(Pizza.DEFAULT_CRUST_SIZE, Pizza.DEFAULT_CRUST_TYPE)
  }
  
  override def toString = s"A $crustSize inch pizza with a $crustType crust"
}

object Pizza {
  val DEFAULT_CRUST_SIZE = 12
  val DEFAULT_CRUST_TYPE = "THIN"
}