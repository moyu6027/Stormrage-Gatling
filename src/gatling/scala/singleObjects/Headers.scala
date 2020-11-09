package singleObjects

object Headers {

	// HTTP GET headers
	val headersGet = Map(
		"Accept" -> "application/json, text/javascript, */*; q=0.01",
		"X-Requested-With" -> "XMLHttpRequest",
//		"Content-type" -> "text/html; charset=UTF-8"
	)

	// HTTP POST headers
	val headersPost = Map(
		"Accept" -> "application/json, application/x-www-form-urlencoded, text/javascript, */*; q=0.01",
		"Content-Type" -> "application/json",
		"X-Requested-With" -> "XMLHttpRequest"
	)

	// AJAX headers
	val headersAjax = Map(
		"Accept" -> "application/json, text/javascript, */*; q=0.01",
		"Content-Type" -> "application/x-www-form-urlencoded",
		"X-Requested-With" -> "XMLHttpRequest",
	)
}