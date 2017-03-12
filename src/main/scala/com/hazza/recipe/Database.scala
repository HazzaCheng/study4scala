package com.hazza.recipe

/**
  * Created by hazzacheng on 17-3-11.
  */

trait FoodCategories {
  case class FoodCategory(name: String, foods: List[Food])
  def allCategories: List[FoodCategory]
}


abstract class Database extends FoodCategories {
  def allFoods: List[Food]
  def allRecipes: List[Recipe]

  def foodNamed(name: String) =
    allFoods.find(f => f.name == name)
}
