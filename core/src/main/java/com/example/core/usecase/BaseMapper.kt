package com.example.core.base

import com.example.core.response.BaseResponse


/**
 * map [Res] returned from network to [Req] object to use it in View.
 * */
interface BaseMapper<Res, Req> {
    //this if you want just the data
    fun mapData(res: Res?): Req

    //this if you want the data with response code and message returned
    fun mapResponse(res: BaseResponse<Res>): BaseResponse<Req>
}

interface BaseMergedMapper<Res, Req> {
    //this if you want just the data
    fun mapData(res: Res?, flag: Int?): Req

    //this if you want the data with response code and message returned
    fun mapResponse(res: BaseResponse<Res>, flag: Int): BaseResponse<Req>
}

/**
 * map [Response] returned from network to [RequestedItem] object to use it in View.
 * @param Response all response returned from api
 * @param ResponseItem is item in list in response
 * @param RequestedItem the item returned in the list you needed to (UI Item model).
 * */
interface BaseListMapper<Response, ResponseItem, RequestedItem> {
    fun mapListData(res: BaseResponse<Response>): List<RequestedItem>
    fun mapItem(res: ResponseItem?): RequestedItem

    //this if you want to return the message and code returned from the server into your view
    fun mapResponse(res: BaseResponse<Response>): BaseResponse<List<RequestedItem>>
}


interface BaseMultipleListMapper<Response, BaseListResponseItem, BaseListRequestedItem, SubListResponseItem, SubListRequestedItem> {
    fun mapBaseListData(res: BaseResponse<Response>): List<BaseListRequestedItem>
    fun mapBaseListItem(res: BaseListResponseItem?): BaseListRequestedItem

    fun mapSubListData(res: BaseResponse<Response>): List<SubListRequestedItem>
    fun mapSubListItem(res: SubListResponseItem?): SubListRequestedItem

    //this if you want to return the message and code returned from the server into your view
    fun mapResponse(res: BaseResponse<Response>): BaseResponse<List<BaseListRequestedItem>>
}