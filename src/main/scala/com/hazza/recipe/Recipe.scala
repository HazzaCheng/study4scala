package com.hazza.recipe

/**
  * Created by hazzacheng on 17-3-11.
  */
class Recipe(
            val name: String,
            val ingredients: List[Food],
            val instructions: String
            ) {
  override def toString: String = name
}
