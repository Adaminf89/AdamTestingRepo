//
//  URLimageConverter.swift
//  ShoppingGoodsApp
//  Created by Adam Infiesto on 4/17/19.
//  Copyright © 2019 Adam Infiesto. All rights reserved.
//

import Foundation
import UIKit

//this is an extension to use for imageviews to parse from string
extension UIImageView
{
    
    func downloadImage(from imgURL: String!)
    {
        let url = URLRequest(url: URL(string: imgURL)!)
        let task = URLSession.shared.dataTask(with: url)
        {
            (data, response, error) in
            
            if error != nil
            {
                print(error!)
                return
            }
            
            //DO NOT DOWNLOAD ON THE MAIN THREAD
            //Queue to UPDATE UI elements
            DispatchQueue.main.async
                {
                self.image = UIImage(data: data!)
            }
            
        }
        task.resume()
    }
}
