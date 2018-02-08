package com.bitclave.node.services.errors

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
class DataNotSaved : RuntimeException("Data not saved") {}
