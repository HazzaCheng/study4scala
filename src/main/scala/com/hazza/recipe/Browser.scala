package com.hazza.recipe

/**
  * Created by hazzacheng on 17-3-11.
  */
abstract class Browser {
  val database: Database

  def recipesUsing(food: Food) =
    database.allRecipes.filter(recipe =>
    recipe.ingredients.contains(food))

  def displayCategory(category: database.FoodCategory): Unit = {
    println(category)
  }
}
