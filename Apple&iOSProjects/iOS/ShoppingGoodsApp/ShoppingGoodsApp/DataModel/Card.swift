//
//  Cards.swift
//  ShoppingGoodsApp
//
//  Created by Adam Infiesto on 4/17/19.
//  Copyright © 2019 Adam Infiesto. All rights reserved.
//

import Foundation
import UIKit

//class used to pull json  objects
public class Card : NSObject
{
    var name : String
    var url : String
    var subtype : String
    var health : String
    
    init(name : String, url : String, subtype : String , health : String)
    {
        self.name = name
        self.url = url
        self.subtype = subtype
        self.health = health
    }
    
    
}
