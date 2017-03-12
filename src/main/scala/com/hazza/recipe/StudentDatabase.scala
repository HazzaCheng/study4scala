package com.hazza.recipe

/**
  * Created by hazzacheng on 17-3-11.
  */
object StudentDatabase extends Database {
  object FrozenFood extends Food("FrozenFood")

  object HeatItUp extends Recipe(
    "heat it up",
    List(FrozenFood),
    "Microwave the 'food' for 10 minutes."
  )

  def allFoods = List(FrozenFood)
  def allRecipes = List(HeatItUp)
  def allCategories = List(
    FoodCategory("endible", List(FrozenFood))
  )

  object StudentBrowser extends Browser {
    val database = StudentDatabase
  }

}
