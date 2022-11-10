/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grupo7.finaldw.api;


import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author alumno
 * @param <T>
 */
public class Representation<T> {
 private long code;

 @Length(max = 3)
 private T data;

 public Representation() {
   // Jackson deserialization
 }

 public Representation(long code, T data) {
   this.code = code;
   this.data = data;
 }

 @JsonProperty
 public long getCode() {
   return code;
 }

 @JsonProperty
 public T getData() {
   return data;
 }
}