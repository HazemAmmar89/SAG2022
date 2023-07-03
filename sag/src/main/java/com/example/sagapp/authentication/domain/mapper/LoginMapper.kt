package com.example.sagapp.authentication.domain.mapper

import com.example.core.base.BaseMapper
import com.example.core.response.BaseResponse
import com.example.data.remote.entities.GlassInfoDto
import com.example.data.remote.entities.LoginDto
import com.example.data.remote.entities.StripePaymentInfoDto
import com.example.data.remote.entities.UserInfoDto
import com.example.sagapp.authentication.domain.model.GlassInfo
import com.example.sagapp.authentication.domain.model.Login
import com.example.sagapp.authentication.domain.model.StripePaymentInfo
import com.example.sagapp.authentication.domain.model.UserInfo

object GlassMapper : BaseMapper<GlassInfoDto,GlassInfo>{
    override fun mapData(res: GlassInfoDto?): GlassInfo {
        return GlassInfo(res!!.boardID, res.code,res.createdAt,res.desc,res.ID)
    }

    override fun mapResponse(res: BaseResponse<GlassInfoDto>): BaseResponse<GlassInfo> {
        TODO("Not yet implemented")
    }

}

object LoginMapper :BaseMapper<LoginDto,Login>{
    override fun mapData(res: LoginDto?): Login {
        return Login(GlassMapper.mapData(res!!.glassDto),StripeMapper.mapData(res.StripePaymentDto),UserMapper.mapData(res.UserDto))
    }

    override fun mapResponse(res: BaseResponse<LoginDto>): BaseResponse<Login> {
        return BaseResponse(mapData(LoginDto(res.data!!.cashPaymentDto,res.data!!.glassDto,res.data!!.StripePaymentDto,res.data!!.UserDto))).apply {
            status =res.status
            message =res.message
        }
    }


}
object StripeMapper:BaseMapper<StripePaymentInfoDto,StripePaymentInfo>{
    override fun mapData(res: StripePaymentInfoDto?): StripePaymentInfo {
        return StripePaymentInfo(res!!.code,res.created_at,res.email,res.id,res.payment_completed,res.payment_option,res.updated_at)
    }

    override fun mapResponse(res: BaseResponse<StripePaymentInfoDto>): BaseResponse<StripePaymentInfo> {
        TODO("Not yet implemented")
    }

}
object UserMapper : BaseMapper<UserInfoDto,UserInfo>{
    override fun mapData(res: UserInfoDto?): UserInfo {
        return UserInfo(res!!.token,res.city,res.createdAt,res.email,res.ID,res.img,res.admin,res.name,res.oauth_token,res.phone,res.relationship,res.updated_at)
    }

    override fun mapResponse(res: BaseResponse<UserInfoDto>): BaseResponse<UserInfo> {
        TODO("Not yet implemented")
    }

}